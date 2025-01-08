package com.working.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table
public class InvestorAndBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ibId;
    
    @ManyToOne
    @JoinColumn(name = "investor_id")
    @JsonIgnore
    private Investor investor;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;
    
    @Column
    private int quantity;

	@Column
    private LocalDateTime purchaseDate;
    
    @Column
    private BigDecimal priceBought;

    public InvestorAndBasket(){
    	this.purchaseDate = LocalDateTime.now();
    }
    
    public InvestorAndBasket(Investor investor, Basket basket, int quantity) {
		super();
		this.investor = investor;
		this.basket = basket;
		this.quantity = quantity;	
		this.purchaseDate = LocalDateTime.now();
	}
    
    public int getIbId() {
		return ibId;
	}

    public void setIbId(int ibId) {
      this.ibId = ibId;
    }

    public LocalDateTime getPurchaseDate() {
      return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
      this.purchaseDate = purchaseDate;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }

    public BigDecimal getPriceBought() {
      return priceBought;
    }

    public void setPriceBought(BigDecimal priceBought) {
      this.priceBought = priceBought;
    }
}
