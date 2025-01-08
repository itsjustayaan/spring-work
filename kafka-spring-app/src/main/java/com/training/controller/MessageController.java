package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.training.consumer.MessageConsumer;
import com.training.producer.MessageProducer;
import com.training.repository.MessageRepository;

@RestController
public class MessageController{
	
	@Autowired
	MessageConsumer messageConsumer;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	MessageProducer messageProducer;
	
	@GetMapping("/getAllMessages")
	public String getAllMessages() {
		return messageRepository.getMessage();
	}
	
	@GetMapping("/sendMessage/{message}")
	public void sendMessage(@PathVariable("message")String msg) {
		messageProducer.sendMessage(msg);
	}
}