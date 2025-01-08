package com.working.services.investorAndBasket;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.working.dao.BasketDAO;
import com.working.dao.InvestorAndBasketDAO;
import com.working.dao.InvestorDAO;
import com.working.model.InvestorAndBasket;
import com.working.services.Investor.InvestorService;

@Service
public class InvestorBasketServiceImpl implements InvestorBasketService {
	
	@Autowired
	InvestorDAO investorDAO;
	
	@Autowired
	BasketDAO basketDAO;
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	InvestorAndBasketDAO investorBasket;

	@Override
	public ResponseEntity<String> buyBasket(InvestorAndBasket investBasket) {
		BigDecimal basketPrice = investBasket.getBasket().calculateBasketPrice();
		double investorBalance = investorService.getInvestorBalance(investBasket.getInvestor().getInvestorId());
		if(basketPrice.doubleValue() <= investorBalance) {
			investorBalance -= basketPrice.doubleValue();
			investBasket.getInvestor().setInvestorBalance(investorBalance);
			investBasket.setPriceBought(basketPrice);
			investorBasket.save(investBasket);
			investorDAO.save(investBasket.getInvestor());
			return new ResponseEntity<>("Basket Bought",HttpStatus.OK);
		}
		return new ResponseEntity<>("Could not buy basket, insufficient balance",HttpStatus.NOT_ACCEPTABLE);
	}

}
