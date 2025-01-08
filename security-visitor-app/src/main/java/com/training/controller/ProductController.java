package com.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	@RequestMapping("/")	
	public String index()
	{
		System.out.println("OFSS CONTROLLER REACHED");
		return "index";		
	}
	
	@RequestMapping("/addProduct")
	public ModelAndView addProduct() {
		ModelAndView view = new ModelAndView();
		view.addObject("addProduct");
		return view;
	}
	
	@RequestMapping("/updateProduct")
	public ModelAndView updateProduct() {
		ModelAndView view = new ModelAndView();
		view.addObject("updateProduct");
		return view;
	}
}