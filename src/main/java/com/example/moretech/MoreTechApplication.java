package com.example.moretech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoreTechApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoreTechApplication.class, args);
    }

}
