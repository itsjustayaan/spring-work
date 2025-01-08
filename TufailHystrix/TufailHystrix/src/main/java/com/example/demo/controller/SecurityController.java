package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@Autowired
	private OrderImpl orderImpl;

	ResponseEntity<String> exchange = null;

	@RequestMapping("/getVisitorsDetails") // http://localhost:6050/getVisitorsDetails
	public String getVisitorsDetails() {

		try {
			exchange = orderImpl.getName();
			return exchange.getBody() + "::status code :" + exchange.getStatusCodeValue();
		} catch (Exception e) {

		}
		return null;

	}
	
	@GetMapping("/getVId")	
	public String getRandomVisitorsId() {

		try {
			exchange = orderImpl.getRandomVisitorId();
			return exchange.getBody() + "::status code :" + exchange.getStatusCodeValue();
		} catch (Exception e) {

		}
		return null;

	}
	
	@GetMapping("/calculate/{duration}/{interest}/{principle}")	
	public String getEMICalc(@PathVariable("principle")double principle, 
								@PathVariable("interest")double interest, 
								@PathVariable("duration")double duration) {

		try {
			exchange = orderImpl.getEMI(principle,interest,duration);
			return exchange.getBody() + "::status code :" + exchange.getStatusCodeValue();
		} catch (Exception e) {

		}
		return null;

	}
}
