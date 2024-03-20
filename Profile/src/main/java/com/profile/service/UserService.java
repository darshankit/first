package com.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.profile.model.User;
import com.profile.repo.UserRepo;

@Service
@Profile(value = {"local","dev","prod"})
public class UserService {
	@Autowired
	private UserRepo repo;
	
	public User saveUser(User user) {
		User save = this.repo.save(user);
		return save;
	}
	
	public List<User> allUser(){
		List<User> findAll = this.repo.findAll();
		return findAll;
	}
	public void deleteUser(int id) {
		this.repo.deleteById(id);
	}
}
