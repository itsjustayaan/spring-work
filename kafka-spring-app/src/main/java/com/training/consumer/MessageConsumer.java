package com.training.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.training.repository.MessageRepository;

@Component
public class MessageConsumer {
	@Autowired
	MessageRepository messageRepository;
	
	@KafkaListener(topics="${myapp.kafka.topic}", groupId="xyz")
	public void consume(String message) {
		System.out.println(message);
		messageRepository.addMessage(message);
	}
}
