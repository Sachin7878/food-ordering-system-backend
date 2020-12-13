package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CartItemModel;

public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {
	
}
