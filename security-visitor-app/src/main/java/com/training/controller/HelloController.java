package com.training.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/home")
	public String index() {
		return "Welcome in Home Page ";
	}
	@RequestMapping("/hello")
	public String hello() {
		return "Hello and welcome";
	}
	@RequestMapping("/customer")
	public String customer() {
		return "Hello customers";
	}
}
