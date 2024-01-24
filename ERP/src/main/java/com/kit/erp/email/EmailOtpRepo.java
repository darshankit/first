package com.kit.erp.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmailOtpRepo extends JpaRepository<EmailOtp, String> {
	
}
