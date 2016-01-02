package com.antyron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author atyron
 */

@SpringBootApplication
@EnableScheduling
public class RpiSensors {
    public static void main(String[] args) {
        SpringApplication.run(RpiSensors.class, args);
    }
}

