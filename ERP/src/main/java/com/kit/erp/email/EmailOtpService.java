package com.kit.erp.email;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailOtpService {
	@Autowired
	private EmailOtpRepo emailOtpRepo;
	
	
	public void saveOtp(EmailOtp emailOtp) {
		emailOtpRepo.save(emailOtp);
	}
	
	public String getOtp(String email) {
		 EmailOtp emailOtp= emailOtpRepo.findById(email).orElseThrow(null);
		 System.out.println(emailOtp);
		return emailOtp.getOtp();
	}
	
	public void deleteOtp(String email) {
		EmailOtp emailOtp= emailOtpRepo.findById(email).orElseThrow(null);
		emailOtpRepo.delete(emailOtp);
	}
}
