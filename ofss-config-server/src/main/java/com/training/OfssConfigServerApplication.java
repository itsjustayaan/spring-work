package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class OfssConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfssConfigServerApplication.class, args);
	}

}
