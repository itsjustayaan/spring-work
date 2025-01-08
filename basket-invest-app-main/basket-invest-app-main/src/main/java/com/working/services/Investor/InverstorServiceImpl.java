package com.working.services.Investor;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.working.dao.AuthorityDAO;
import com.working.dao.InvestorAndBasketDAO;
import com.working.dao.InvestorDAO;
import com.working.dao.UserRepository;
import com.working.model.Authority;
import com.working.model.Basket;
import com.working.model.Investor;
import com.working.model.InvestorAndBasket;
import com.working.model.Users;

import jakarta.transaction.Transactional;

@Service
public class InverstorServiceImpl implements InvestorService{
	
	@Autowired
	InvestorDAO investorDAO;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorityDAO authorityDAO;
	
	@Autowired
	InvestorAndBasketDAO investorBasket;
	
	@Autowired
    JavaMailSender javaMailSender;
	
	@Override
	public ResponseEntity<String> createInvestor(Investor investor){
		if(investor.getInvestorName() == "") {
			return new ResponseEntity<>("Investor Name cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investor.getInvestorEmail() == "") {
			return new ResponseEntity<>("Investor Email cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investor.getInvestorPassword() == "") {
			return new ResponseEntity<>("Investor Password cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investor.getInvestorBalance() < 0) {
			return new ResponseEntity<>("Investor Balance cannot be negative",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investorDAO.findById(investor.getInvestorId()).orElse(null) != null) {
			return new ResponseEntity<>("Investor with this ID Exists",HttpStatus.CONFLICT);
		}
		else {
			try {
				investorDAO.save(investor);
				Set<Authority> authorities = new HashSet<>();
			    Authority authority = new Authority(investor.getInvestorEmail(),"ROLE_INVESTOR");
			    authorities.add(authority);
			    Users user = new Users(investor.getInvestorEmail(), investor.getInvestorPassword(), true, authorities);
			    userRepository.save(user);
				return new ResponseEntity<>("Investor created",HttpStatus.CREATED);
			} catch(Exception e) {
				return new ResponseEntity<>("Investor could not be created",HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@Override
	public ResponseEntity<String> updateInvestor(Investor investor){
		if(investor.getInvestorEmail() == "") {
			return new ResponseEntity<>("Investment Advisor Email cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investor.getInvestorPassword() == "") {
			return new ResponseEntity<>("Investment Advisor Password cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else {
			Optional<Investor> iaTemp = investorDAO.findById(investor.getInvestorId());
			Users user = userRepository.findByUsername(iaTemp.get().getInvestorEmail());
			Authority authority = authorityDAO.findByUsername(iaTemp.get().getInvestorEmail());
			if(investor.getInvestorName()==null)
				investor.setInvestorName(iaTemp.get().getInvestorName());
			
			if(investor.getInvestorEmail()==null)
				investor.setInvestorEmail(iaTemp.get().getInvestorEmail());
			
			if(investor.getInvestorPassword()==null)
				investor.setInvestorPassword(iaTemp.get().getInvestorPassword());
			
			if(investor.getInvestorBalance()==0)
				investor.setInvestorBalance(iaTemp.get().getInvestorBalance());
				
			investorDAO.save(investor);
			user.setUsername(investor.getInvestorEmail());
			user.setPassword(investor.getInvestorPassword());
			authority.setUsername(investor.getInvestorEmail());
			userRepository.save(user);
			authorityDAO.save(authority);
			return new ResponseEntity<>("Investor Updated",HttpStatus.OK);
		}
	}
	
	
	@Override
	public ResponseEntity<String> deleteInvestor(int investorId){
		if(investorDAO.existsById(investorId)) {
			investorDAO.deleteById(investorId);
			return new ResponseEntity<>("Investor with this ID has been deleted",HttpStatus.OK);
		}
		return new ResponseEntity<>("Investor with this ID doesn't exist",HttpStatus.CONFLICT);
	}
	
	@Override
	public ResponseEntity<List<Investor>> findAllInvestors(){
		List<Investor> allIa= (List<Investor>) investorDAO.findAll();
		if(allIa.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(allIa,HttpStatus.NOT_ACCEPTABLE);
	}
	
	public boolean ifExistsInvestor(int investorId) {
		return investorDAO.existsById(investorId);
	}
	
	public ResponseEntity<List<Investor>> findInvestor(String investorName){
		return new ResponseEntity<>(investorDAO.findByInvestorName(investorName),HttpStatus.OK);
	}
	
	public ResponseEntity<List<Investor>> findByEmailInvestor(String investorEmail){
		return new ResponseEntity<>(investorDAO.findByInvestorEmail(investorEmail),HttpStatus.OK);
	}
	
	public boolean existsByEmail(String email) {
		return investorDAO.existsByInvestorEmail(email);
	}

	@Override
	public ResponseEntity<String> updateInvestorBalance(int investorId, double balance) {
		Investor inv = investorDAO.findById(investorId).get();
		inv.setInvestorBalance(inv.getInvestorBalance()+balance);
		investorDAO.save(inv);
		return new ResponseEntity<>("Investor Balance Updated",HttpStatus.OK);
	}
	
	@Transactional
	public void sellBasket(Investor investor, Basket basket, int quantityToSell) throws Exception {

	    // Fetch all entries where the investor owns this basket (FIFO order by purchase date)
	    PriorityQueue<InvestorAndBasket> basketQueue = new PriorityQueue<>(Comparator.comparing(InvestorAndBasket::getPurchaseDate));
	    basketQueue.addAll(investorBasket.findByInvestorAndBasket(investor, basket));

	    // If the investor doesn't own this basket at all
	    if (basketQueue.isEmpty()) {
	        throw new Exception("You don't own this basket. Short selling is not allowed.");
	    }

	    int remainingToSell = quantityToSell;
	    BigDecimal totalAmountReceived = BigDecimal.ZERO;

	    // Process the queue and sell the baskets based on the oldest purchases first
	    while (!basketQueue.isEmpty() && remainingToSell > 0) {
	        InvestorAndBasket currentBasket = basketQueue.poll(); // Get the oldest purchase

	        // If the current basket entry has enough quantity to satisfy the sale
	        if (currentBasket.getQuantity() >= remainingToSell) {
	            BigDecimal basketPrice = basket.calculateBasketPrice();
	            totalAmountReceived = totalAmountReceived.add(basketPrice.multiply(new BigDecimal(remainingToSell)));
	            
	            // Update the basket's quantity or remove if all sold
	            if (currentBasket.getQuantity() == remainingToSell) {
	                investorBasket.delete(currentBasket); // Remove entry if fully sold
	            } else {
	                currentBasket.setQuantity(currentBasket.getQuantity() - remainingToSell);
	                investorBasket.save(currentBasket); // Update remaining quantity
	            }
	            remainingToSell = 0; // All requested baskets sold

	        } else {
	            // Sell whatever quantity is available in this entry
	            BigDecimal basketPrice = basket.calculateBasketPrice();
	            totalAmountReceived = totalAmountReceived.add(basketPrice.multiply(new BigDecimal(currentBasket.getQuantity())));

	            remainingToSell -= currentBasket.getQuantity(); // Reduce remaining quantity to sell
	            investorBasket.delete(currentBasket); // Remove fully sold basket
	        }
	    }

	    // Check if there was enough quantity to satisfy the entire sell request
	    if (remainingToSell > 0) {
	        throw new Exception("Not enough quantity to sell. You own fewer baskets than requested.");
	    }

	    // Update the investor's balance with the total amount received
	    investor.setInvestorBalance(investor.getInvestorBalance() + totalAmountReceived.doubleValue());
	    investorDAO.save(investor);
	}

	 @Override
	 public double getInvestorBalance(int investorId) {
	     Investor investor = investorDAO.findById(investorId).get();
	     double balance = investor.getInvestorBalance();
	     return balance;
	 }
	 
	 @Override
	 public String generateRandomPassword(int length) {
	        Random random = new Random();
	        StringBuilder password = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            int charType = random.nextInt(3);
	            if (charType == 0) { password.append((char) ('0' + random.nextInt(10))); } 
	            else if (charType == 1) { password.append((char) ('A' + random.nextInt(26))); } 
	            else { password.append((char) ('a' + random.nextInt(26))); }
	        }
	        return password.toString();
	 }
	 
	 @Override
	 public void sendEmail(String to, String password) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("New Password for Login");
	        message.setText("Your new password is: " + password +". Use this to login into your investor dashboard");
	        javaMailSender.send(message);
	 }
}
	

