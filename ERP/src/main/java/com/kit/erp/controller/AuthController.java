package com.kit.erp.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kit.erp.email.EmailOtp;
import com.kit.erp.email.EmailOtpService;
import com.kit.erp.email.EmailService;
import com.kit.erp.entity.StudentDetails;
import com.kit.erp.entity.auth.LoginRequest;
import com.kit.erp.entity.auth.LoginResponse;
import com.kit.erp.entity.auth.OtpRequest;
import com.kit.erp.entity.auth.OtpResponse;
import com.kit.erp.service.StudentService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmailOtpService emailOtpService;

	@Autowired
	private EmailService emailService;

	//This mapping is to register a student details
	@PostMapping("/save")
	public ResponseEntity<StudentDetails> saveStudent(@RequestBody StudentDetails student) {
		StudentDetails studentDetails = studentService.saveStudent(student);
		return ResponseEntity.ok(studentDetails);
	}
	
	//This mapping is to compare the username and password which are in database with the entered username and password.
	@RequestMapping("/login")
	public ResponseEntity<OtpResponse> login(@RequestBody LoginRequest login) {
		OtpResponse response;
		int code;

		boolean f = this.doAuthentication(login.getEmail(), login.getPassword());//calling doAuthentication method to authenticating username and password
		if (f) {
			code = 200;
			String otp = this.generateOTP(login.getEmail());//calling generateOTP method to generate random OTP.
			String to = login.getEmail();
			String subject = "OTP For login";
			String message = "This is the 6 digit OTP for login " + otp + " \n Don't Share this with anyone";
			emailService.sendSimpleMessage(to, subject, message);
			response = new OtpResponse("otp sent to your mail", otp);
		} else {
			response = new OtpResponse("authentication failed", "201");
			code = 201;
		}
		return new ResponseEntity<OtpResponse>(response, HttpStatusCode.valueOf(code));

	}

	@RequestMapping("/otp-validation")
	public ResponseEntity<LoginResponse> validateOtp(@RequestBody OtpRequest otpRequest) {
		String otp = emailOtpService.getOtp(otpRequest.getEmail());
		LoginResponse response;
		if (otpRequest.getOtp().equals(otp)) {
			response = new LoginResponse("login successful", otp);
			System.out.println("login successful");
			emailOtpService.deleteOtp(otpRequest.getEmail());
		} else {
			response = new LoginResponse("invalid Otp", otp);
			System.out.println("login failed");
		}
		return ResponseEntity.ok(response);

	}

	public boolean doAuthentication(String email, String password) {
		boolean flag = false;
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(authentication);
			flag = true;
		} catch (BadCredentialsException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		return flag;

	}
	
	public String generateOTP(String email) {
		Random random = new Random();
		int otpValue = 100_000 + random.nextInt(900_000);
		String otp = String.valueOf(otpValue);
		EmailOtp e = new EmailOtp();
		e.setEmail(email);
		e.setOtp(otp);
		emailOtpService.saveOtp(e);

		// Here you would save the OTP and associated email to your database
		// For simplicity, we're just returning the OTP
		return otp;
	}

}
