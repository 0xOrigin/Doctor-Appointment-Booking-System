package com.xorigin.doctorappointmentmanagementsystem.core.emails;

import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Email {
    private final String from;
    private final String fromName;
    private final String subject;
    private final String body;
    private final List<String> to;
    private final String replyTo;
    private final List<String> cc;
    private final List<String> bcc;
    private final List<Attachment> attachments;
    private final boolean isHtml;
    private final Date sentAt;

    private Email(Builder builder) {
        this.subject = builder.subject;
        this.body = builder.body;
        this.to = builder.to;
        this.replyTo = builder.replyTo;
        this.from = builder.from;
        this.fromName = builder.fromName;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.attachments = builder.attachments;
        this.isHtml = builder.isHtml;
        this.sentAt = builder.sentAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Attachment {
        private final String filename;
        private final Resource resource;

        public Attachment(String filename, Resource resource) {
            this.filename = filename;
            this.resource = resource;
        }

        public String getFilename() {
            return filename;
        }

        public Resource getResource() {
            return resource;
        }

    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public List<String> getTo() {
        return to;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getFrom() {
        return from;
    }

    public String getFromName() {
        return fromName;
    }

    public List<String> getCc() {
        return cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public static class Builder {
        private String from;
        private String fromName;
        private String subject;
        private String body;
        private final List<String> to = new ArrayList<>();
        private String replyTo;
        private final List<String> cc = new ArrayList<>();
        private final List<String> bcc = new ArrayList<>();
        private final List<Attachment> attachments = new ArrayList<>();
        private boolean isHtml = false;
        private Date sentAt;

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder to(List<String> to) {
            if (to != null) {
                this.to.addAll(to);
            }
            return this;
        }

        public Builder to(String to) {
            if (to != null && !to.isEmpty()) {
                this.to.add(to);
            }
            return this;
        }

        public Builder replyTo(String replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder fromName(String fromName) {
            this.fromName = fromName;
            return this;
        }

        public Builder cc(List<String> cc) {
            if (cc != null) {
                this.cc.addAll(cc);
            }
            return this;
        }

        public Builder cc(String cc) {
            if (cc != null && !cc.isEmpty()) {
                this.cc.add(cc);
            }
            return this;
        }

        public Builder bcc(List<String> bcc) {
            if (bcc != null) {
                this.bcc.addAll(bcc);
            }
            return this;
        }

        public Builder bcc(String bcc) {
            if (bcc != null && !bcc.isEmpty()) {
                this.bcc.add(bcc);
            }
            return this;
        }

        public Builder attachment(String filename, Resource resource) {
            this.attachments.add(new Attachment(filename, resource));
            return this;
        }

        public Builder attachments(List<Attachment> attachments) {
            if (attachments != null) {
                this.attachments.addAll(attachments);
            }
            return this;
        }

        public Builder isHtml(boolean isHtml) {
            this.isHtml = isHtml;
            return this;
        }

        public Builder sentAt(Date sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public Email build() {
            return new Email(this);
        }

    }

}
