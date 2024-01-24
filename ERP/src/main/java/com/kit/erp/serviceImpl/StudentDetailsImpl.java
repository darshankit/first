package com.kit.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.kit.erp.entity.StudentDetails;
import com.kit.erp.repo.StudentRepo;
import com.kit.erp.service.StudentService;

@Service
public class StudentDetailsImpl implements StudentService {
	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private BCryptPasswordEncoder bcry;

	@Override
	public StudentDetails saveStudent(StudentDetails student) {
		student.setPassword(bcry.encode(student.getPassword()));
		StudentDetails s = studentRepo.save(student);

		return s;
	}

}
