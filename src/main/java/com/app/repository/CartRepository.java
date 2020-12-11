package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CartModel;
import com.app.model.UserModel;

public interface CartRepository extends JpaRepository<CartModel, Long> {
	CartModel findByCustomer(UserModel customer);
}
