package com.working.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.BasketDAO;
import com.working.dao.InvestorAndBasketDAO;
import com.working.dao.InvestorDAO;
import com.working.dao.UserRepository;
import com.working.model.Basket;
import com.working.model.Investor;
import com.working.model.InvestorAndBasket;
import com.working.model.Sell;
import com.working.services.Basket.BasketServiceImpl;
import com.working.services.Investor.InvestorService;
import com.working.services.investorAndBasket.InvestorBasketService;

@RestController
@RequestMapping("investor")
public class InvestorController {
    
	@Autowired
	InvestorService investorService;
	
	@Autowired
	InvestorDAO investorDAO;
	
	@Autowired
	BasketDAO basketDAO;
	
	@Autowired
	InvestorBasketService investorAndBasket;
	
	@Autowired
	InvestorAndBasketDAO investorAndBasketDAO;
	
	@Autowired
	BasketServiceImpl basketService;
	
	@Autowired
	UserRepository userRepository;
	
		
	@PutMapping("update")
	public ResponseEntity<String> updateInvestor(Principal principal, @RequestBody Investor investor){
		investor.setInvestorId(investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorId());
		return investorService.updateInvestor(investor);
	}
	
//	@DeleteMapping("delete")
//	public ResponseEntity<String> deleteInvestor(Principal principal){
//		return investorService.deleteInvestor(investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorId());
//	}
	
	@GetMapping("getAllBasket")
	public ResponseEntity<List<Basket>> listAllBasket(){
		return basketService.findAllBasket();
	}
	
	@PutMapping("addBalance")
	public ResponseEntity<String> addBalance(Principal principal, @RequestBody Investor investor){
		return investorService.updateInvestorBalance(investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorId(), investor.getInvestorBalance());
	}
	
	@PutMapping("withdrawBalance")
	public ResponseEntity<String> withdrawBalance(Principal principal, @RequestBody double withdrawalAmount) {
	    Investor inv = investorDAO.findByInvestorEmail(principal.getName()).get(0);
	    if (inv.getInvestorBalance() >= withdrawalAmount) {
	        double updatedBalance = inv.getInvestorBalance() - withdrawalAmount;
	        inv.setInvestorBalance(updatedBalance);
	        investorDAO.save(inv);
	        return new ResponseEntity<>("Withdrawal successful. New balance: " + updatedBalance, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Insufficient balance for withdrawal", HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping("checkBalance")
	public ResponseEntity<String> getBalance(Principal principal){
		double balance = investorService.getInvestorBalance(investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorId());
		return new ResponseEntity<>("Investor balance: " + balance, HttpStatus.OK);
	}
	
	@GetMapping("myBaskets")
	public ResponseEntity<Investor> myBaskets(Principal principal) {
	    Investor investor = investorDAO.findByInvestorEmail(principal.getName()).get(0);
	    return new ResponseEntity<>(investor, HttpStatus.OK);
	}

	
	@PostMapping("sell")
	public ResponseEntity<String> sellBasket(Principal principal, @RequestBody Sell sell) {
		Investor investor = investorDAO.findById(investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorId()).orElseThrow(() -> new RuntimeException("Investor not found"));
		Basket basket = basketDAO.findById(sell.getBasketId()).orElseThrow(() -> new RuntimeException("Basket not found"));
		try {
			investorService.sellBasket(investor, basket, sell.getQuantity());
			return new ResponseEntity<>("Basket sold successfully.",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Basket could not be sold",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	 
	 @PostMapping("buy")
	 public ResponseEntity<String> setInvestorBasket(Principal principal, @RequestBody InvestorAndBasket investBasket){
		 System.out.println(investBasket);
		 Investor inv = investorDAO.findByInvestorEmail(principal.getName()).get(0);
		 investBasket.setInvestor(inv);
		 Basket bs = basketDAO.findById(investBasket.getBasket().getBasketId()).orElseThrow(() -> new RuntimeException("Basket not found"));
		 investBasket.setBasket(bs);
		 investBasket.setPurchaseDate(LocalDateTime.now());
		 return investorAndBasket.buyBasket(investBasket);
	 }

	@GetMapping("viewAr")
	public ResponseEntity<Map<String, Object>> viewAr(Principal principal) {
	    List<InvestorAndBasket> investorAndBasketList = investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorAndBasketList();
	    BigDecimal strikePrice = BigDecimal.ZERO;
	    BigDecimal currentPrice = BigDecimal.ZERO;

	    for (InvestorAndBasket investorAndBasket : investorAndBasketList) {
	        strikePrice = strikePrice.add(investorAndBasket.getPriceBought()).multiply(new BigDecimal(investorAndBasket.getQuantity()));
	        currentPrice = currentPrice.add(investorAndBasket.getBasket().calculateBasketPrice()).multiply(new BigDecimal(investorAndBasket.getQuantity()));
	    }

	    BigDecimal absoluteReturn = currentPrice.subtract(strikePrice);
	    BigDecimal percentageReturn = absoluteReturn.divide(currentPrice, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));

	    Map<String, Object> response = new HashMap<>();
	    response.put("absoluteReturn", absoluteReturn);
	    response.put("percentageReturn", percentageReturn);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
}