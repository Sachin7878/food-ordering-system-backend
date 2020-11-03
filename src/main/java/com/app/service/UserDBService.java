package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.UserModel;
import com.app.repository.UserRepository;

@Service
public class UserDBService {

	@Autowired
	private UserRepository repo;

	public String deleteUser(String email) {
		UserModel u = repo.findByEmail(email);
		repo.delete(u);
		return "User with email::" + u.getEmail() + " deleted successfully!";
	}
}
