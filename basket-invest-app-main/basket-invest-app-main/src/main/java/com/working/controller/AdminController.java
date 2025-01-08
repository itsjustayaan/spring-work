package com.working.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.InvestmentAdvisorDAO;
import com.working.model.InvestmentAdvisor;
import com.working.model.Investor;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorService;
import com.working.services.Investor.InvestorService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	InvestmentAdvisorService investmentAdvisorService;
	
	@Autowired
	InvestmentAdvisorDAO investmentAdvisorDAO;
	
	@PostMapping("createAdvisor")
	public ResponseEntity<String> createAdvisor(@RequestBody InvestmentAdvisor investmentAdvisor){
		return investmentAdvisorService.createInvestmentAdvisor(investmentAdvisor);
	}
	
	@DeleteMapping("deleteAdvisor")
	public ResponseEntity<String> deleteAdvisor(@RequestBody InvestmentAdvisor investmentAdvisor){
		System.out.println(investmentAdvisor.getIaId());
		return investmentAdvisorService.deleteInvestmentAdvisor(investmentAdvisor.getIaId());
	}
	
	@GetMapping("findAllIA")
	public ResponseEntity<List<InvestmentAdvisor>> getAllInvestorAdvisor(){
		return investmentAdvisorService.findAllInvestmentAdvisor();
	}
	
	@PostMapping("createInvestor")
	public ResponseEntity<String> setInvestor(@RequestBody Investor investor){
		return investorService.createInvestor(investor);
	}
	
	
}