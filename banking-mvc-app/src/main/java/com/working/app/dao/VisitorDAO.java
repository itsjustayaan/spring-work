package com.working.app.dao;

import java.util.List;

import com.working.app.model.Visitor;

public interface VisitorDAO {
	
	public void createVisitor(Visitor visitor);
	public void updateVisitor(Visitor visitor);
	public void deleteVisitor(int visitorId);
	public List<Visitor> findVisitors();
	public boolean isVisitorExists(int visitorId);
	public Visitor findVisitors(int visitorId);

}
