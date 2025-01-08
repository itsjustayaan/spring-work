package com.working.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Stock {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String isin;
	
	@Column
	private String stockName;
	
	@Column
	private String industry;
	
	@Column
	private String stockSymbol;
	
	@Column
	private String stockPrice;
	
	public Stock() {
		
	}

	public Stock(String isin, String stockName, String industry, String stockSymbol, String stockPrice) {
		super();
		this.isin = isin;
		this.stockName = stockName;
		this.industry = industry;
		this.stockSymbol = stockSymbol;
		this.stockPrice = stockPrice;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}
}
