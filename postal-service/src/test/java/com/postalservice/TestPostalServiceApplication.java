package com.postalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestPostalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(PostalServiceApplication::main).with(TestPostalServiceApplication.class).run(args);
    }

}
