package com.vadel.projectms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjectMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectMsApplication.class, args);
	}

}
