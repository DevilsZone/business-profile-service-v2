package com.intuit.interview.businessprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BusinessProfileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessProfileServiceApplication.class, args);
    }

}
