package com.kit.erp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kit.erp.entity.StudentDetails;
@Repository
public interface StudentRepo extends JpaRepository<StudentDetails, String>{
	public Optional<StudentDetails> findByEmail(String email);
}
