package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BankingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingEurekaServerApplication.class, args);
	}

}
