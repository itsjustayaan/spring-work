package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MessagingApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingApiAppApplication.class, args);
	}
	
	@GetMapping("/greetings")
	public String printGreetings() {
		return "Hi Oracle!!";
	}
	@GetMapping("")
	public String guestGreetings() {
		return "Hi Guest!!";
	}

}
