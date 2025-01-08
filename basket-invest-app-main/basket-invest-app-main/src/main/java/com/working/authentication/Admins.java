package com.working.authentication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Admins {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int adminId;
	
	@Column
	private String adminEmail;
	
	@Column
	private String adminPass;
	
	public Admins(int adminId, String adminEmail, String adminPass) {
		super();
		this.adminId = adminId;
		this.adminEmail = adminEmail;
		this.adminPass = adminPass;
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	
}
