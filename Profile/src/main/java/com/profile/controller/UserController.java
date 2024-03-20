package com.profile.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.profile.model.User;
import com.profile.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return ResponseEntity.ok(this.service.saveUser(user));
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> allUser(){
		return ResponseEntity.ok(this.service.allUser());
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String>deleteUser(@PathVariable int id){
		this.service.deleteUser(id);
		return ResponseEntity.ok("deletion success");
	}
}
