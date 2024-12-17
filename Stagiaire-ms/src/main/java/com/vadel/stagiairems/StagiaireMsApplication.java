package com.vadel.stagiairems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StagiaireMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StagiaireMsApplication.class, args);
    }

}
