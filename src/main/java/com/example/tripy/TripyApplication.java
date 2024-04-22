package com.example.tripy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TripyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripyApplication.class, args);
    }

}
