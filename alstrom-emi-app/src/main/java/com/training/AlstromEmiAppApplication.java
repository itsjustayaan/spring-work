package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableDiscoveryClient
public class AlstromEmiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlstromEmiAppApplication.class, args);
	}

}
