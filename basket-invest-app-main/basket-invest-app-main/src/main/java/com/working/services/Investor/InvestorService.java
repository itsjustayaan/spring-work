package com.working.services.Investor;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.working.model.Basket;
import com.working.model.Investor;

public interface InvestorService {
	public ResponseEntity<String> createInvestor(Investor investor);
	public ResponseEntity<String> updateInvestor(Investor investor);
	public ResponseEntity<String> deleteInvestor(int investorId);
	public ResponseEntity<String> updateInvestorBalance(int investorId, double balance);
	public double getInvestorBalance(int investorId);
	public ResponseEntity<List<Investor>> findAllInvestors();
	public boolean ifExistsInvestor(int investorId);
	public ResponseEntity<List<Investor>> findInvestor(String investorName);
	public ResponseEntity<List<Investor>> findByEmailInvestor(String investorEmail);
	public boolean existsByEmail(String email); 
	public void sellBasket(Investor investor, Basket basket, int quantityToSell)throws Exception;
	public String generateRandomPassword(int length);
	public void sendEmail(String to, String password);
}
