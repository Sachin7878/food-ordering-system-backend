package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.DAOUser;

@Repository
public interface UserRepository extends JpaRepository<DAOUser, Long> {
	DAOUser findByEmail(String email);
}