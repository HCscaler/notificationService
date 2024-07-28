package com.knowit.notificationService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.notificationService.entities.EmailRequest;
import com.knowit.notificationService.services.EmailService;
@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	EmailService emailService;
	@PostMapping
	public String EmailSender(@RequestBody EmailRequest emailRequest)
	{
		String str = emailService.sendMail(emailRequest);
		return str;
	}
}
