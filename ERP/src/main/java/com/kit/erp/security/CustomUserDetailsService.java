package com.kit.erp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kit.erp.entity.StudentDetails;
import com.kit.erp.repo.StudentRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		StudentDetails user = studentRepo.findByEmail(username).orElseThrow(null);

		return user;
	}

}
