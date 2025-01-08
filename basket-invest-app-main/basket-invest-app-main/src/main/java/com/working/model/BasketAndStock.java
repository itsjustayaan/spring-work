package com.working.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
@JsonIgnoreProperties({"basket"})
public class BasketAndStock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int bsId;
	
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "stock_isin")
    private Stock stock;

    private int quantity;
    
    public BasketAndStock() {
    	
    }

	public BasketAndStock(Basket basket, Stock stock, int quantity) {
		super();
		this.basket = basket;
		this.stock = stock;
		this.quantity = quantity;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBsId() {
		return bsId;
	}

	public void setBsId(int bsId) {
		this.bsId = bsId;
	}
}
