package com.training;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="credentials.user")
public class ConfigProps {
	
	private String name;			
	private String password;

	public ConfigProps() {
		// TODO Auto-generated constructor stub
	}


	public ConfigProps(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "ConfigProps [name=" + name + ", password=" + password + "]";
	}


}
