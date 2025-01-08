package com.training;

import org.springframework.stereotype.Component;

@Component
public class Customer {
	private int amount;
	
	public Customer() {
		this.amount = 999;
	}
	
	public Customer(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String payBill(int amount) {
		this.amount = amount;
		return "Customer pays the bill amount: "+amount;
	}
}
