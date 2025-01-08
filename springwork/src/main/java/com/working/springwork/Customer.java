package com.working.springwork;

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
	
	public void payBill(int amount) {
		this.amount = amount;
		System.out.println("Customer pays bill of : "+ amount);
	}
	
	public void checkout() {
		System.out.println("Customer has checkout with amount: "+ amount);
	}
}
