package com.working.springwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Orders {
	
	@Autowired
	Customer customer;
//
//	@Autowired
//	@Qualifier("getCustomer")
//	Customer c1;
//
//	@Autowired
//	@Qualifier("cust2")
//	Customer c2;
	
	@Autowired
	@Qualifier("person")
	Loan loan1;
	
	public void takeOrders() {
		System.out.println("Order Taken");
		customer.payBill(10);
		customer.checkout();
//		c1.payBill();
//		c2.payBill();
	}
	
	public String takeLoanOrders() {
		return loan1.applyLoan();
	}
}
