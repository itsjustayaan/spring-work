package com.working.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.working.model.InvestmentAdvisor;

@Repository
public interface InvestmentAdvisorDAO extends JpaRepository<InvestmentAdvisor,Integer>{
	public List<InvestmentAdvisor> findByIaEmail(String iaEmail);
	public List<InvestmentAdvisor> findByIaName(String iaName);
}
