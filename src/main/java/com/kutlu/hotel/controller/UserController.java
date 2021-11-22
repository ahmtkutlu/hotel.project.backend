package com.kutlu.hotel.controller;

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

import com.kutlu.hotel.exception.ResourceNotFoundException;
import com.kutlu.hotel.model.User;
import com.kutlu.hotel.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("users")
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@PostMapping("users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + id));
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		User userDetails = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + id));
		userDetails.setName(user.getName());
		userDetails.setMail(user.getMail());
		User updatedUser = userRepository.save(userDetails);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		User userDetails = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + id));
		userRepository.delete(userDetails);
		//userRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("result", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}