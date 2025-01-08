package com.training.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessageRepository {
public List<String> list = new ArrayList<>();
	
	public void addMessage(String message) {
		list.add(message);
	}
	
	public String getMessage(){
		return list.toString();
	}
}
