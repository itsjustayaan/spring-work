package com.training.services;

import java.util.List;

import com.training.model.Visitor;

public interface VisitorService {

	public String createVisitor(Visitor visitor);
	public String updateVisitor(Visitor visitor);
	public String deleteVisitor(int visitorId);
	
	public Visitor findVisitors(int visitorId);
	public List<Visitor> findVisitors();
	public List<Visitor> findVisitors(String name);
	public List<Visitor> findVisitors(int minId, int maxId);
	
	public List<Visitor> findVisitorsByPurpose(String purpose);
	public List<Visitor> findVisitorsByMoblieNumber(String number);
	public boolean isVisitorExists(int visitorId);
}