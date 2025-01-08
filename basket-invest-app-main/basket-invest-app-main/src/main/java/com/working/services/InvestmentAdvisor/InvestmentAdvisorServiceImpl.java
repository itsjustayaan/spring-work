package com.working.services.InvestmentAdvisor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.working.dao.AuthorityDAO;
import com.working.dao.InvestmentAdvisorDAO;
import com.working.dao.UserRepository;
import com.working.model.Authority;
import com.working.model.InvestmentAdvisor;
import com.working.model.Users;

import jakarta.transaction.Transactional;

@Service
public class InvestmentAdvisorServiceImpl implements InvestmentAdvisorService{
	

	@Autowired
	InvestmentAdvisorDAO investmentAdvisorDAO;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorityDAO authorityDAO;
  
  @Autowired
  JavaMailSender javaMailSender;
	
	@Override
	public ResponseEntity<String> createInvestmentAdvisor(InvestmentAdvisor investmentAdvisor) {
		if (investmentAdvisor.getIaName() == null || investmentAdvisor.getIaName().isEmpty()) {
		    return new ResponseEntity<>("Investment Advisor Name cannot be empty", HttpStatus.NOT_ACCEPTABLE);
		}
		if (investmentAdvisor.getIaEmail() == null || investmentAdvisor.getIaEmail().isEmpty()) {
		    return new ResponseEntity<>("Investment Advisor Email cannot be empty", HttpStatus.NOT_ACCEPTABLE);
		}
		if (investmentAdvisor.getIaPassword() == null || investmentAdvisor.getIaPassword().isEmpty()) {
		    return new ResponseEntity<>("Investment Advisor Password cannot be empty", HttpStatus.NOT_ACCEPTABLE);
		}
		if (!investmentAdvisorDAO.findByIaEmail(investmentAdvisor.getIaEmail()).isEmpty()) {
		    return new ResponseEntity<>("Investment Advisor with this Email Exists", HttpStatus.CONFLICT);
		}
		if (investmentAdvisorDAO.findById(investmentAdvisor.getIaId()).isPresent()) {
		    return new ResponseEntity<>("Investment Advisor with this ID Exists", HttpStatus.CONFLICT);
		}
		try {
		    investmentAdvisorDAO.save(investmentAdvisor);
		    Set<Authority> authorities = new HashSet<>();
		    Authority authority = new Authority(investmentAdvisor.getIaEmail(), "ROLE_INVESTMENT_ADVISOR");
		    authorities.add(authority);
		    Users user = new Users(investmentAdvisor.getIaEmail(), investmentAdvisor.getIaPassword(), true, authorities);
		    userRepository.save(user);
		    return new ResponseEntity<>("Investment Advisor created", HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>("Investment Advisor could not be created", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> updateInvestmentAdvisor(InvestmentAdvisor investmentAdvisor) {
		if(investmentAdvisor.getIaEmail() == "") {
			return new ResponseEntity<>("Investment Advisor Email cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investmentAdvisor.getIaPassword() == "") {
			return new ResponseEntity<>("Investment Advisor Password cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else {
			Optional<InvestmentAdvisor> iaTemp = investmentAdvisorDAO.findById(investmentAdvisor.getIaId());
			Users user = userRepository.findByUsername(iaTemp.get().getIaEmail());
			Authority authority = authorityDAO.findByUsername(iaTemp.get().getIaEmail());
			if(investmentAdvisor.getIaName()==null)
				investmentAdvisor.setIaName(iaTemp.get().getIaName());
			
			if(investmentAdvisor.getIaEmail()==null)
				investmentAdvisor.setIaEmail(iaTemp.get().getIaEmail());
			
			if(investmentAdvisor.getIaPassword()==null)
				investmentAdvisor.setIaPassword(iaTemp.get().getIaPassword());
				
			investmentAdvisorDAO.save(investmentAdvisor);
			user.setUsername(investmentAdvisor.getIaEmail());
			user.setPassword(investmentAdvisor.getIaPassword());
			authority.setUsername(investmentAdvisor.getIaEmail());
			userRepository.save(user);
			authorityDAO.save(authority);
			return new ResponseEntity<>("Investment Advisor Updated",HttpStatus.OK);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteInvestmentAdvisor(int iaId) {
	    Optional<InvestmentAdvisor> advisorOpt = investmentAdvisorDAO.findById(iaId);
	    if (advisorOpt.isPresent()) {
	        InvestmentAdvisor advisor = advisorOpt.get();
	        Authority auth = authorityDAO.findByUsername(advisor.getIaEmail());
	        if (auth != null) {
	            String email = advisor.getIaEmail();
	            investmentAdvisorDAO.deleteById(iaId);
	            userRepository.deleteByUsername(email);
	            authorityDAO.delete(auth);
	        } else {
	            String email = advisor.getIaEmail();
	            investmentAdvisorDAO.deleteById(iaId);
	            userRepository.deleteByUsername(email);
	        }
	        return new ResponseEntity<>("Investor with this ID has been deleted", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("Investor with this ID doesn't exist", HttpStatus.CONFLICT);
	}



	@Override
	public ResponseEntity<List<InvestmentAdvisor>> findAllInvestmentAdvisor() {
		List<InvestmentAdvisor> allIa = investmentAdvisorDAO.findAll();
		if(allIa.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(allIa,HttpStatus.OK);
	}

	@Override
	public boolean ifExistsInvestmentAdvisor(int iaId) {
		return investmentAdvisorDAO.existsById(iaId);
	}

	@Override
	public ResponseEntity<List<InvestmentAdvisor>> findInvestmentAdvisor(String iaName) {
		return new ResponseEntity<>(investmentAdvisorDAO.findByIaName(iaName),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<InvestmentAdvisor>>  findByEmailInvestmentAdvisor(String iaEmail) {
		return new ResponseEntity<>(investmentAdvisorDAO.findByIaEmail(iaEmail),HttpStatus.OK);
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
