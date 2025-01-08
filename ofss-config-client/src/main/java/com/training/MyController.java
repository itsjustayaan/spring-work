package com.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


	@Autowired
	ConfigProps configProps;

	@RequestMapping("/displayProperties")
	public String test(){
	return "********Team Name***********"+configProps.getName()+":password is :"+configProps.getPassword();
	}

	
}
