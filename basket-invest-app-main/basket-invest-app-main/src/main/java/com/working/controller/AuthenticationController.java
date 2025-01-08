package com.working.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.working.dao.InvestmentAdvisorDAO;
import com.working.dao.InvestorDAO;
import com.working.dao.UserRepository;
import com.working.model.InvestmentAdvisor;
import com.working.model.Investor;
import com.working.model.Users;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorService;
import com.working.services.Investor.InvestorService;

@Controller
public class AuthenticationController {
	
	@Autowired
	InvestorDAO investorDAO;
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	InvestmentAdvisorService investmentAdvisorService;
	
	@Autowired
	InvestmentAdvisorDAO investmentAdvisorDAO;
	
    @RequestMapping("/login")
    public String login(Principal principal, Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("errorMsg", "Your username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("msg", "You have been logged out successfully.");
        }
        
//        System.out.println(principal.getName());
        
        return "login"; 
    }


    @GetMapping("/logoutPage")
    public String logoutPage(Authentication authentication, Model model) {
        String username = "";
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        model.addAttribute("username", username);
        return "logout";  
    }
    
    @GetMapping("/forgetPass/investor/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email) {
		List<Investor> inv = investorDAO.findByInvestorEmail(email);
		if(inv != null) {
		    String randomPassword = investorService.generateRandomPassword(10);
		    Investor investor = inv.get(0);
		    investor.setInvestorPassword(randomPassword);
		    investorDAO.save(investor);
		    Users user = userRepository.findByUsername(email);
		    user.setPassword(randomPassword);
		    userRepository.save(user);
		    investorService.sendEmail(email, randomPassword);
		    return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
		}
		return new ResponseEntity<>("ERROR User Not Registered!", HttpStatus.NOT_FOUND);
	}
    
    @GetMapping("/forgetPass/ia/{email}")
	public ResponseEntity<String> forgotIaPassword(@PathVariable String email) {
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
		} else {
			return new ResponseEntity<>("ERROR User Not Registered!", HttpStatus.NOT_FOUND);
		}
	}
    
    @GetMapping("/testForgetPass/ia/{email}")
	public ResponseEntity<String> testForgotIaPassword(@PathVariable String email) {
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
		    return ResponseEntity.ok("Email sent successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR User Not Registered!");
		}
	}
    
    @GetMapping("/testForgetPass/investor/{email}")
	public ResponseEntity<String> testForgotPassword(@PathVariable String email) {
		List<Investor> inv = investorDAO.findByInvestorEmail(email);
		if(inv != null) {
		    String randomPassword = investorService.generateRandomPassword(10);
		    Investor investor = inv.get(0);
		    investor.setInvestorPassword(randomPassword);
		    investorDAO.save(investor);
		    Users user = userRepository.findByUsername(email);
		    user.setPassword(randomPassword);
		    userRepository.save(user);
		    investorService.sendEmail(email, randomPassword);
		    return ResponseEntity.ok("Email sent successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR User Not Registered!");
		}
	}
    
}