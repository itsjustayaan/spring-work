package com.working.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.InvestmentAdvisorDAO;
import com.working.dao.UserRepository;
import com.working.model.Basket;
import com.working.model.InvestmentAdvisor;
import com.working.model.Users;
import com.working.services.Basket.BasketServiceImpl;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorService;
import com.working.services.Investor.InvestorService;

@RestController
@RequestMapping("ia")
public class InvestmentAdvisorController {
	
	@Autowired
	InvestmentAdvisorService investmentAdvisorService;
	
	@Autowired
	InvestmentAdvisorDAO investmentAdvisorDAO;

	@Autowired
	InvestorService investorService;
	
	@Autowired
	BasketServiceImpl basketImpl;
	
	@Autowired
	UserRepository userRepository;
	
	@PutMapping("update")
	public ResponseEntity<String> updateAdvisor(Principal principal,@RequestBody InvestmentAdvisor investmentAdvisor){
		investmentAdvisor.setIaId(investmentAdvisorDAO.findByIaEmail(principal.getName()).get(0).getIaId());
		return investmentAdvisorService.updateInvestmentAdvisor(investmentAdvisor);
	}
	
	@PutMapping("testUpdate")
	public ResponseEntity<InvestmentAdvisor> updateInvestmentAdvisor(Principal principal,@RequestBody InvestmentAdvisor investmentAdvisor) {
		investmentAdvisor.setIaId(investmentAdvisorDAO.findByIaEmail(principal.getName()).get(0).getIaId());
	    return ResponseEntity.ok(investmentAdvisor);
	}

	
	@PostMapping("createBasket")
	public ResponseEntity<String> setBasket(Principal principal, @RequestBody Basket basket){
		basket.setIaId_ref(investmentAdvisorDAO.findByIaEmail(principal.getName()).get(0).getIaId());
		return basketImpl.createBasket(basket);
	}
	
	@PostMapping("testCreateBasket")
	public ResponseEntity<String> setIaBasket(@RequestBody Basket basket){
		return ResponseEntity.ok(basketImpl.createBasket(basket).toString());
	}
	
	@GetMapping("/forgetPass/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email) {
		List<InvestmentAdvisor> inv = investmentAdvisorDAO.findByIaEmail(email);
		if(inv != null) {
		    String randomPassword = investmentAdvisorService.generateRandomPassword(10);
		    InvestmentAdvisor investor = inv.get(0);
		    investor.setIaPassword(randomPassword);
		    investmentAdvisorDAO.save(investor);
		    Users user = userRepository.findByUsername(email);
		    user.setPassword(randomPassword);
		    userRepository.save(user);
		    investmentAdvisorService.sendEmail(email, randomPassword);
		    return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
		}
		return new ResponseEntity<>("ERROR User Not Registered!", HttpStatus.NOT_FOUND);
	}
}
