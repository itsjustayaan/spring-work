package com.training.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("ofssmsg", "Oracle FSS login failed");
			model.addAttribute("errorMsg", "Your username and password are invalid.");
			model.addAttribute("trainer", "Mohammad Tufail Ahmed");
			model.addAttribute("salary", "Salary Credited!!");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}
	
	@RequestMapping("/accessDenied")
	public String accessDenied(){
		return "accessDenied";
	}
}