package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.UserModel;
import com.app.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/api/account")
	public ResponseEntity<UserModel> getAccountDetails(@CurrentSecurityContext(expression = "authentication.name") String userEmail) {
		UserModel currentUser = userRepo.findByEmail(userEmail);
		return ResponseEntity.ok(currentUser);
	}
}
