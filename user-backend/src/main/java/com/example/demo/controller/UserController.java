package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

/**
 * Controller of User - For response Rest Api
 *
 * @author Min Ku Nam
 * @version 1.0
 * @created 2020/09/22
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	// Rest Api
	// get all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	} 
	
	// create User
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	// get user by id
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn´t exist with this id : " +id));
		return ResponseEntity.ok(user);
	}
	
	// update user 
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userInfo) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn´t exist with this id : " +id));
		user.setFirstName(userInfo.getFirstName());
		user.setLastName(userInfo.getLastName());
		user.setEmailId(userInfo.getEmailId());
		
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	// delete user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn´t exist with this id : " +id));
		userRepository.delete(user);
		Map<String, Boolean> result = new HashMap<>();
		result.put("deleted successfully", Boolean.TRUE);
		return ResponseEntity.ok(result);
	}
	
}
