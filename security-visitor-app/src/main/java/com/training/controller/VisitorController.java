package com.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitorController {
	
	@GetMapping("/sayHello")
	public String sayHello() {
		return "Hi Guest";
	}
}
