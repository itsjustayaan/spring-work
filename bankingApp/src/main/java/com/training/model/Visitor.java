package com.training.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="visitors")
@NamedQuery(name="Visitor.findByOFSSMobileNumber",
query = "from Visitor u where u.mobileNumber = ?1")
public class Visitor {
	@Id
	private int visitorId;
	
	@Column
	private String visitorName;
	
	@Column
	private String purpose;
	
	@Column
	private String mobileNumber;
	
	public Visitor() {
		
	}
	
	public Visitor(int visitorId, String visitorName, String purpose, String mobileNumber) {
		this.visitorId = visitorId;
		this.visitorName = visitorName;
		this.purpose = purpose;
		this.mobileNumber = mobileNumber;
	}

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Visitor [visitorId=" + visitorId + ", visitorName=" + visitorName + ", purpose=" + purpose
				+ ", mobileNumber=" + mobileNumber + "]";
	}
}
