package com.working.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Basket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int basketId;
	
	@Column
	private String basketName;
	
	@Column
	private String basketSummary;
	
	@Column
	private int iaId_ref;
	
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<BasketAndStock> basketStockList;
  
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InvestorAndBasket> investorAndBasketList;
    
    public Basket () {
    	
    }

	public Basket(String basketName, String basketSummary, InvestmentAdvisor investmentAdvisor) {
	super();
	this.basketName = basketName;
	this.basketSummary = basketSummary;
	}

	public int getBasketId() {
		return basketId;
	}

	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}

	public String getBasketName() {
		return basketName;
	}

	public void setBasketName(String basketName) {
		this.basketName = basketName;
	}

	public String getBasketSummary() {
		return basketSummary;
	}

	public void setBasketSummary(String basketSummary) {
		this.basketSummary = basketSummary;
	}

	public List<BasketAndStock> getBasketStockList() {
		return basketStockList;
	}

	public void setBasketStockList(List<BasketAndStock> basketStockList) {
		this.basketStockList = basketStockList;
	}

	public int getIaId_ref() {
		return iaId_ref;
	}

	public void setIaId_ref(int iaId_ref) {
		this.iaId_ref = iaId_ref;
	}

	public List<InvestorAndBasket> getInvestorAndBasketList() {
		return investorAndBasketList;
	}

	public void setInvestorAndBasketList(List<InvestorAndBasket> investorAndBasketList) {
		this.investorAndBasketList = investorAndBasketList;
	}
	
	public BigDecimal calculateBasketPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (BasketAndStock basketStock : basketStockList) {
            Stock stock = basketStock.getStock();
            BigDecimal stockPrice = new BigDecimal(stock.getStockPrice());
            BigDecimal stockQuantity = new BigDecimal(basketStock.getQuantity());
            
            totalPrice = totalPrice.add(stockPrice.multiply(stockQuantity));
        }

        return totalPrice;
    }
}
