package com.working.services.investorAndBasket;

import org.springframework.http.ResponseEntity;

import com.working.model.InvestorAndBasket;

public interface InvestorBasketService {
	public ResponseEntity<String> buyBasket(InvestorAndBasket investBasket);
}
