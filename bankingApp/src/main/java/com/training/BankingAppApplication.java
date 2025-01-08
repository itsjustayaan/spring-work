package com.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableCaching
@EnableDiscoveryClient
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

	@Autowired
	Orders orders;
	
	@Autowired
	Loan loan;
	
	@GetMapping("/displayOrders")
	public String orders() {
		return orders.takeOrders();
	}
	
	@GetMapping("/loanStatus")
	public String loanStatus() {
		return loan.applyLoan();
	}
}
