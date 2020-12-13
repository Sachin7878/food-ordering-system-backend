package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CartItemModel;

public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {
	List<CartItemModel> findByCartId(Long cartId);
}
