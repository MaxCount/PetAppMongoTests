package com.petapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class PetAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetAppApplication.class, args);
    }

}
