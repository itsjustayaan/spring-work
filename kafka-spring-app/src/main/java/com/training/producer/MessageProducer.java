package com.training.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
	
	@Value("${myapp.kafka.topic}")
	public String topicName;
	
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	
	public void sendMessage(String message) {
		kafkaTemplate.send(topicName,message);
	}
}
