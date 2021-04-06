package com.example.initial_frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class InitialFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitialFrameApplication.class, args);
    }

}
