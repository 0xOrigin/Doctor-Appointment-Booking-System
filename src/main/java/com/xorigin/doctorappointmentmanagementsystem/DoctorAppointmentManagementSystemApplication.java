package com.xorigin.doctorappointmentmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class DoctorAppointmentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorAppointmentManagementSystemApplication.class, args);
	}

}
