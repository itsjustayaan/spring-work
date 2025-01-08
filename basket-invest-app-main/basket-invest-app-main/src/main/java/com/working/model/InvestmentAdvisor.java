package com.working.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InvestmentAdvisor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iaId;
	
	@Column(nullable=false)
	private String iaName;
	
	@Column(nullable=false, unique=true)
	private String iaEmail;
	
	@Column(nullable=false)
	private String iaPassword;
	
	protected InvestmentAdvisor() {
		
	}
	
	public InvestmentAdvisor(String iaName, String ia_Email, String ia_Password) {
		super();
		this.iaName=iaName;
		this.iaEmail = ia_Email;
		this.iaPassword = ia_Password;
	}
	
	public InvestmentAdvisor(int iaId, String iaName, String iaEmail, String iaPassword) {
	    this.iaId = iaId;
	    this.iaName = iaName;
	    this.iaEmail = iaEmail;
	    this.iaPassword = iaPassword;
	}


	public String getIaName() {
		return iaName;
	}

	public void setIaName(String iaName) {
		this.iaName = iaName;
	}

	public int getIaId() {
		return iaId;
	}

	public void setIaId(int iaId) {
		this.iaId = iaId;
	}

	public String getIaEmail() {
		return iaEmail;
	}

	public void setIaEmail(String iaEmail) {
		this.iaEmail = iaEmail;
	}

	public String getIaPassword() {
		return iaPassword;
	}

	public void setIaPassword(String iaPassword) {
		this.iaPassword = iaPassword;
	}

	@Override
	public String toString() {
		return "InvestmentAdvisor [iaId=" + iaId + ", iaEmail=" + iaEmail + ", iaPassword=" + iaPassword + "]";
	}
}
