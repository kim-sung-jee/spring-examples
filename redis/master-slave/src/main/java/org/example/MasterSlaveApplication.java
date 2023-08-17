package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MasterSlaveApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveApplication.class);
    }
}