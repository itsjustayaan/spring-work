package com.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.model.Visitor;

@Repository
public interface VisitorDAO extends CrudRepository<Visitor, Integer>{
	
	public List<Visitor> findByVisitorName(String name);
	public List<Visitor> findByVisitorIdBetween(int min, int max);
	public List<Visitor> findByPurpose(String purpose);
	public List<Visitor> findByMobileNumber(String mobileNumber);
	
	@Query(value="select visitorName from Visitor where visitorId = :vId")
	public String findByMyCustom(int vId);
	
	public List<Visitor> findByOFSSMobileNumber(String mobileNumber);
}
