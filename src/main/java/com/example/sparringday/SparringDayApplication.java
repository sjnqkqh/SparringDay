package com.example.sparringday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SparringDayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparringDayApplication.class, args);
    }

}
