package com.postalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostalServiceApplication.class, args);
    }

}
