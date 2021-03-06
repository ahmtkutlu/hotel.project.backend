package com.kutlu.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.kutlu.hotel.model.AdminUser;
import com.kutlu.hotel.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("users")
	public List<AdminUser> getAllUsers() {
		return this.userRepository.findAll();
	}

	@PostMapping("users")
	public AdminUser createUser(@RequestBody AdminUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<AdminUser> getUserById(@PathVariable Long id) {
		AdminUser user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + id));
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<AdminUser> updateUser(@PathVariable Long id, @RequestBody AdminUser user){
		AdminUser userDetails = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + id));
		userDetails.setUserName(user.getUsername());
		userDetails.setMail(user.getMail());
		AdminUser updatedUser = userRepository.save(userDetails);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		AdminUser userDetails = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + id));
		userRepository.delete(userDetails);
		//userRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("result", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}