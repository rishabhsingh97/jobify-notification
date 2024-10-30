package com.rsuniverse.jobify_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JobifyNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobifyNotificationApplication.class, args);
    }

}
