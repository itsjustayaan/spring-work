package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.training.services.EMICalcService;

@RestController
public class EMICalculatorContorller {
	
	@Autowired
	EMICalcService emical;
	
	@GetMapping("calculateEMI/{duration}/{interest}/{principle}")
	public ResponseEntity<String> getMonthlyEMI	(@PathVariable("principle")double principle, 
													@PathVariable("interest")double interest, 
														@PathVariable("duration")double duration ){
		return new ResponseEntity<String>(String.valueOf(emical.calculateEMI(principle, interest, duration)),HttpStatus.OK);
		
	}
}
