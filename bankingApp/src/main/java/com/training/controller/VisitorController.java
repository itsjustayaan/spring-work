package com.training.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dao.VisitorDAO;
import com.training.model.Visitor;
import com.training.services.VisitorService;

@RestController
@RequestMapping("visitors")
public class VisitorController {
	
	@Autowired
	VisitorService visitorService;
	
	@Autowired
	VisitorDAO visitorDAO;
	
	@GetMapping
	public List<Visitor> getVisitors() {
		return visitorService.findVisitors();		
	}
	
	private String errorBack = "Visitor does not exist";
	
	@GetMapping("{visitorId}")
	public ResponseEntity<String> getSingleVisitors(@PathVariable("visitorId")int visitorId) {
		if(visitorService.findVisitors(visitorId)==null) {
			ResponseEntity<String> temp = new ResponseEntity<>(this.errorBack,HttpStatus.NOT_FOUND);
			return temp;
		}
		return new ResponseEntity<String>(visitorService.findVisitors(visitorId).toString(),HttpStatus.OK);
	}
	
	@GetMapping("findVisitor/{visitorName}")
	public ResponseEntity<String> findVisitor(@PathVariable("visitorName")String visitorName) {
		if(visitorService.findVisitors(visitorName)==null) {
			ResponseEntity<String> temp = new ResponseEntity<>(this.errorBack,HttpStatus.NOT_FOUND);
			return temp;
		}
		ResponseEntity<String> temp = new ResponseEntity<>(visitorService.findVisitors(visitorName).toString(),HttpStatus.OK);
		return temp;
	}
	
	@GetMapping("findVisitor/{fromId}/{toId}")
	public ResponseEntity<String> findVisitor(@PathVariable("fromId")int visitorid1, @PathVariable("toId")int visitorid2) {
		if(visitorService.findVisitors(visitorid1,visitorid2)==null) {
			ResponseEntity<String> temp = new ResponseEntity<>(this.errorBack,HttpStatus.NOT_FOUND);
			return temp;
		}
		ResponseEntity<String> temp = new ResponseEntity<>(visitorService.findVisitors(visitorid1,visitorid2).toString(),HttpStatus.OK);
		return temp;
	}
	
	@PostMapping	
	public ResponseEntity<String> saveVisitors(@RequestBody Visitor visitor) {
		ResponseEntity<String> responseEntity = null;
		String result = visitorService.createVisitor(visitor);
		if(result.equals("Negative Visitor ID entered")) {
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_ACCEPTABLE);
		}
		else if(result.equals("Visitor already exists")) {
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CONFLICT);
		}
		else {
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
		}
		
		return responseEntity;
	}
	
	@DeleteMapping("{visitorId}")
	public ResponseEntity<String> deleteVisitors(@PathVariable("visitorId")int visitorId) {
		String result = visitorService.deleteVisitor(visitorId);
		if(result.equals("Visitor Deleted")) {
			ResponseEntity<String> temp = new ResponseEntity<>(result,HttpStatus.OK);
			return temp;
		}
		ResponseEntity<String> temp = new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		return temp;
	}
	
	@PutMapping
	public ResponseEntity<String> updateVisitors(@RequestBody Visitor visitor) {
		String result = visitorService.updateVisitor(visitor);
		if(result.equals("Visitor Updated")) {
			ResponseEntity<String> temp = new ResponseEntity<>(result,HttpStatus.OK);
			return temp;
		}
		ResponseEntity<String> temp = new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		return temp;
	}
	
	@PutMapping("{visitorId}")
	public String updateVisitors(@PathVariable("visitorId")int visitorId, @RequestBody Visitor visitor) {
		return "Visitor Updated by id: "+ visitorId+" Visitor Details: "+visitor;
	}
	
	@GetMapping("findVisitor/purpose/{purpose}")
	public ResponseEntity<String> findVisitorPurpose(@PathVariable("purpose") String purpose) {
		if(visitorService.findVisitorsByPurpose(purpose)==null) {
			ResponseEntity<String> temp = new ResponseEntity<>(this.errorBack,HttpStatus.NOT_FOUND);
			return temp;
		}
		ResponseEntity<String> temp = new ResponseEntity<>(visitorService.findVisitorsByPurpose(purpose).toString(),HttpStatus.OK);
		return temp;
	}

	@GetMapping("findVisitor/mobile/{mobileNumber}")
	public ResponseEntity<String> findVisitorMobile(@PathVariable("mobileNumber") String mobileNumber) {
		if(visitorService.findVisitorsByMoblieNumber(mobileNumber)==null) {
			ResponseEntity<String> temp = new ResponseEntity<>(this.errorBack,HttpStatus.NOT_FOUND);
			return temp;
		}
		ResponseEntity<String> temp = new ResponseEntity<>(visitorService.findVisitorsByMoblieNumber(mobileNumber).toString(),HttpStatus.OK);
		return temp;
	}
	
	private Random rand = new Random();
	
	@GetMapping("generatedVisitorId")
	public String getRandomVisitorId() {
		
		return "V00" +this.rand.nextInt(100,90000);
	}
}
