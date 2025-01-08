package com.working.springwork;

import org.springframework.stereotype.Component;

@Component
public class Loan {
	
	private int loanAccountNumber;
	private String customerName;
	private int loanAmount;
	
	public Loan() {
		this.loanAccountNumber = 0;
		this.customerName = "";
		this.loanAmount = 0;
	}
	
	public Loan(int loanAccountNumber, String customerName, int loanAmount) {
		this.loanAccountNumber = loanAccountNumber;
		this.customerName = customerName;
		this.loanAmount = loanAmount;
	}
	
	public String applyLoan() {
		return "loanAccountNumber=" + loanAccountNumber + ", customerName=" + customerName + ", loanAmount="
				+ loanAmount+ "Loan Applied";
	}
	
	
}
