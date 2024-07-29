package com.techgenerale.springboottesting;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class SpringBootTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestingApplication.class, args);
    }


    @PostConstruct
    private void appInit(){
        System.out.println("Application started");
    }
}
