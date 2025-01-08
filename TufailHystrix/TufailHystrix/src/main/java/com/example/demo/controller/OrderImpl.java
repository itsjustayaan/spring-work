package com.example.demo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class OrderImpl {


	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<String> ofssmymethod() {
		System.out.println("*********************Micoservice is not available*******************");
		return new ResponseEntity<String>("Visitor Microservice is not available",HttpStatus.CONFLICT);

	}
	
	public ResponseEntity<String> ofssmymethod(double principle, double interest, double duration) {
		System.out.println("*********************Micoservice is not available*******************");
		return new ResponseEntity<String>("Visitor Microservice is not available",HttpStatus.CONFLICT);
		
	}

	@HystrixCommand(fallbackMethod = "ofssmymethod")
	public ResponseEntity<String> getName() {
		try {
			System.out.println("*********************Inside URI ...*******************");
			URI endPoint = URI.create("http://localhost:9090/visitors");
			return restTemplate.getForEntity(endPoint, String.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}	
	
	@HystrixCommand(fallbackMethod = "ofssmymethod")
	public ResponseEntity<String> getRandomVisitorId() {
		try {
			System.out.println("*********************Inside URI ...*******************");
			URI endPoint = URI.create("http://localhost:9090/visitors/generatedVisitorId");
			return restTemplate.getForEntity(endPoint, String.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
	
	@HystrixCommand(fallbackMethod = "ofssmymethod")
	public ResponseEntity<String> getEMI(double principle, double interest, double duration) {
		try {
			System.out.println("*********************Inside URI ...*******************");
			URI endPoint = URI.create("http://localhost:6788/calculateEMI/"+duration+"/"+interest+"/"+principle);
			return restTemplate.getForEntity(endPoint, String.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
}















