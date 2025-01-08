package com.working.model;

public class Sell {
	
	private int basketId;
	private int quantity;
	
	public Sell() {
		
	}
	
	public Sell(int basketId, int quantity) {
		super();
		this.basketId = basketId;
		this.quantity = quantity;
	}
	
	public int getBasketId() {
		return basketId;
	}

	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
