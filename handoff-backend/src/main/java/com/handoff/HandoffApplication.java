package com.handoff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HandoffApplication {
    public static void main(String[] args) {
        SpringApplication.run(HandoffApplication.class, args);
    }
}
