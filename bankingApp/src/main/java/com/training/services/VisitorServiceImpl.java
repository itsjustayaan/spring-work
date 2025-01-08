package com.training.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.training.dao.VisitorDAO;
import com.training.model.Visitor;

@Service
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	VisitorDAO visitorDAO;

	@Override
	public String createVisitor(Visitor visitor) {
		if(visitor.getVisitorId() < 0) {
			return("Negative Visitor ID entered");
		}
		else if(visitorDAO.existsById(visitor.getVisitorId())) {
			return("Visitor already exists");
		}
		else {
			visitorDAO.save(visitor);
			return("Visitor saved");
		}
	}

	@Override
	public String updateVisitor(Visitor visitor) {
		if(isVisitorExists(visitor.getVisitorId())) {
			visitorDAO.save(visitor);
			return "Visitor Updated";
		}
		else {
			return "Visitor not found!";
		}
	}

	@Override
	public String deleteVisitor(int visitorId) {
		if(isVisitorExists(visitorId)) {
			visitorDAO.deleteById(visitorId);
			return "Visitor Deleted";
		}
		else {
			return "Visitor not found!";
		}
	}
	
	@Override
	public List<Visitor> findVisitors() {
		return (List<Visitor>) visitorDAO.findAll();
	}

	@Override
	@Cacheable(value="visitors",key="#p0")
	public Visitor findVisitors(int visitorId) {
		if(isVisitorExists(visitorId)) {
			return visitorDAO.findById(visitorId).get();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Visitor> findVisitors(String name) {
		if(visitorDAO.findByVisitorName(name).size() > 0)
			return visitorDAO.findByVisitorName(name);
		
		return null;
	}

	@Override
	public List<Visitor> findVisitors(int minId, int maxId) {
		if(visitorDAO.findByVisitorIdBetween(minId, maxId).size() > 0)
			return visitorDAO.findByVisitorIdBetween(minId, maxId);
			
		return null;
	}
	
	@Override
	public List<Visitor> findVisitorsByPurpose(String purpose) {
		if(visitorDAO.findByPurpose(purpose).size() > 0)
			return visitorDAO.findByPurpose(purpose);
		
		return null;
	}

	@Override
	public List<Visitor> findVisitorsByMoblieNumber(String number) {
		if(!visitorDAO.findByMobileNumber(number).isEmpty())
			return visitorDAO.findByMobileNumber(number);
		
		return null;
	}

	@Override
	public boolean isVisitorExists(int visitorId) {
		return visitorDAO.existsById(visitorId);
	}
}
