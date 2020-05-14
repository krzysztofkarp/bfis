package com.bfis.email;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	
	void send(String to, SimpleMailMessage message);
	void send(String to, String template, Map<String, Object> properites) throws MessagingException;

}
