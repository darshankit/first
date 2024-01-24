package com.kit.erp.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	 @Autowired
	 private JavaMailSenderImpl javaMailSenderImpl;
	 
	 public void sendSimpleMessage(String to, String subject, String text) {
		 SimpleMailMessage message= new SimpleMailMessage();
		 message.setFrom("darshanachars18@mail.com");
		 message.setTo(to);
		 message.setSubject(subject);
		 message.setText(text);
		 javaMailSenderImpl.send(message);
		 }
}
