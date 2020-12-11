package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CartItemModel;
import com.app.model.CartModel;

public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {
	List<CartItemModel> findByCart(CartModel cart);
}
