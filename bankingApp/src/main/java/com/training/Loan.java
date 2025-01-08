package com.training;

import org.springframework.stereotype.Component;

@Component
public class Loan {
	
	public String applyLoan() {
		return "Your loan application has been approved";
	}
}
