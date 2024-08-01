package com.knowit.notificationService.kafkaCunsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowit.notificationService.controllers.EmailController;
import com.knowit.notificationService.entities.EmailRequest;
import com.knowit.notificationService.services.EmailService;

@Service
public class KafkaCunsumer {

	@Autowired
	EmailService emailService;
	
	@Autowired
	ObjectMapper objectMapper;

	@KafkaListener(topics = Constant.sendMail, groupId = Constant.group_Id)
	public void sendMail(String emailRequestStr) throws JsonMappingException, JsonProcessingException
	{
		System.out.println("email consume : "+emailRequestStr);
		EmailRequest emailRequest = objectMapper.readValue(emailRequestStr, EmailRequest.class);
		
		
//		EmailRequest emailRequest = new EmailRequest("maheshbharati321@gmail.com","notification","working properly");
		emailService.sendMail(emailRequest);
	}
}
