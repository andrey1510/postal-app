package com.compositeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCompositeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CompositeServiceApplication::main).with(TestCompositeServiceApplication.class).run(args);
    }

}
