package com.working.app.dao;

import java.util.List;

import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.working.app.model.Visitor;

@Repository
public class VisitorDAOImpl implements VisitorDAO {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public void createVisitor(Visitor visitor) {
		hibernateTemplate.getSessionFactory()
		.getCurrentSession()
		.setHibernateFlushMode(FlushMode.AUTO);
		hibernateTemplate.save(visitor);
	}

	@Override
	@Transactional
	public void updateVisitor(Visitor visitor) {
		// TODO Auto-generated method stub
		hibernateTemplate.getSessionFactory()
		.getCurrentSession()
		.setHibernateFlushMode(FlushMode.AUTO);
		hibernateTemplate.update(visitor);
	}

	@Override
	@Transactional
	public void deleteVisitor(int visitorId) {
		// TODO Auto-generated method stub
		Visitor v = new Visitor();
		v.setVisitorId(visitorId);
		hibernateTemplate.getSessionFactory()
		.getCurrentSession()
		.setHibernateFlushMode(FlushMode.AUTO);
		hibernateTemplate.delete(v);
	}

	@Override
	public List<Visitor> findVisitors() {
		// TODO Auto-generated method stub
		return (List<Visitor>) hibernateTemplate.find("from Visitor");
	}

	@Override
	public boolean isVisitorExists(int visitorId) {
		// TODO Auto-generated method stub
		Visitor check = hibernateTemplate.get(Visitor.class, visitorId);
		return (check != null);
	}

	@Override
	public Visitor findVisitors(int visitorId) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Visitor.class, visitorId);
	}

}
