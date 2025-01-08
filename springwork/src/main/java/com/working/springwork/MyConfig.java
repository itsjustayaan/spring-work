package com.working.springwork;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.working.springwork")
@EnableAspectJAutoProxy
@Configuration
public class MyConfig {
	
	@Bean
	public Customer getCustomer() {
		return new Customer(67900);
	}

	@Bean(name="person")
	public Loan personalhomeLoanApplicant() {
		return new Loan(12345, "Ayaan", 10000);
	}
	
	@Bean(name="home")
	public Loan homeLoanApplicant() {
		return new Loan(13456, "Mitra", 20000);
	}
	
	@Bean(name="gold")
	public Loan goldLoanApplicant() {
		return new Loan(24789, "Harsh", 30000);
	}
	
	@Bean(name="education")
	public Loan educationLoanApplicant() {
		return new Loan(35689, "Amar", 25000);
	}
}
