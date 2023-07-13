package com.example.sofiyauserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SofiyaUserServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SofiyaUserServiceApplication.class, args);
	}

}
