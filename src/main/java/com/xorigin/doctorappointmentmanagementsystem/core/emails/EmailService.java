package com.xorigin.doctorappointmentmanagementsystem.core.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final Boolean isMailEnabled;
    private final String defaultFrom;
    private final String defaultFromName;

    public EmailService(
            JavaMailSender mailSender,
            @Value("${spring.mail.enabled:true}") Boolean isMailEnabled,
            @Value("${spring.mail.username}") String from,
            @Value("${spring.mail.from-name}") String fromName
    ) {
        this.mailSender = mailSender;
        this.isMailEnabled = isMailEnabled;
        this.defaultFrom = from;
        this.defaultFromName = fromName;
    }

    private MimeMessage createMessage(){
        return mailSender.createMimeMessage();
    }

    private MimeMessageHelper createMessageHelper(MimeMessage message, boolean multipart) {
        try {
            return new MimeMessageHelper(message, multipart);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to create MimeMessageHelper", e);
        }
    }

    private MimeMessageHelper createMessageHelper(MimeMessage message) {
        return createMessageHelper(message, true);
    }

    private String getFrom(Email email) {
        if (email.getFrom() != null && !email.getFrom().isEmpty())
            return email.getFrom();

        return defaultFrom;
    }

    private String getFromName(Email email) {
        if (email.getFromName() != null && !email.getFromName().isEmpty())
            return email.getFromName();

        return defaultFromName;
    }

    private void setEmailAttributes(MimeMessageHelper helper, Email email) {
        try {
            helper.setFrom(getFrom(email), getFromName(email));
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(), email.isHtml());
            helper.setTo(
                email.getTo()
                    .stream()
                    .filter(s -> s != null && !s.isEmpty())
                    .toArray(String[]::new)
            );

            if (email.getReplyTo() != null && !email.getReplyTo().isEmpty())
                helper.setReplyTo(email.getReplyTo());

            if (email.getSentAt() != null)
                helper.setSentDate(email.getSentAt());

            helper.setCc(
                email.getCc()
                    .stream()
                    .filter(s -> s != null && !s.isEmpty())
                    .toArray(String[]::new)
            );

            helper.setBcc(
                email.getBcc()
                    .stream()
                    .filter(s -> s != null && !s.isEmpty())
                    .toArray(String[]::new)
            );

            email.getAttachments()
                .forEach(
                    attachment -> {
                        try {
                            helper.addAttachment(attachment.getFilename(), attachment.getResource());
                        } catch (MessagingException e) {
                            throw new RuntimeException("Failed to add attachment: " + attachment.getFilename(), e);
                        }
                    }
                );
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to set email attributes", e);
        }
    }

    private boolean send(MimeMessage message) {
        // Disable email sending in case of testing
        if (!isMailEnabled)
            return false;

        mailSender.send(message);
        return true;
    }

    @Async
    public void send(Email email) {
        MimeMessage message = createMessage();
        MimeMessageHelper helper = createMessageHelper(message);
        setEmailAttributes(helper, email);
        boolean isSent = send(message);
        String logMessage = isSent ? "Email sent successfully" : "Email sending not enabled";
        log.info(logMessage);
    }

}
