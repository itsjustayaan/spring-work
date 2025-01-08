package com.training.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	
	@RequestMapping("/")	
	public String index(Model model){
		model.addAttribute("userName",getUsername());
		System.out.println("OFSS CONTROLLER REACHED");
		return "index";		
	}
	
	@RequestMapping("/calculatorEMI")
	public String calculatorEMI(Model model) {
		model.addAttribute("userName",getUsername());
		return "calculatorEMI";
	}
	public String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
