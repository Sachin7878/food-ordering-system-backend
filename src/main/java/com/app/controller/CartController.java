package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CartItemModel;
import com.app.model.CartModel;
import com.app.model.UserModel;
import com.app.repository.CartItemRepository;
import com.app.repository.CartRepository;
import com.app.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CartController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	CartRepository cartRepo;

	@Autowired
	CartItemRepository cartItemRepo;

	@GetMapping("/cart")
	public CartModel getCustomerCart(@CurrentSecurityContext(expression = "authentication.name") String userEmail) {

		UserModel currentUser = userRepo.findByEmail(userEmail);
		CartModel customerCart = cartRepo.findByCustomer(currentUser);
		if (customerCart == null) {
			customerCart = new CartModel();
			customerCart.setCustomer(currentUser);
			cartRepo.save(customerCart);
		}
		return customerCart;
	}
	
	
	@PostMapping("/cart")
	public ResponseEntity<?> addItemToCart(@RequestBody CartItemModel cartItemFromUser,
		@CurrentSecurityContext(expression = "authentication.name") String userEmail) {
		UserModel currentUser = userRepo.findByEmail(userEmail);
		CartModel customerCart = cartRepo.findByCustomer(currentUser);
		List<CartItemModel> items = customerCart.getCartItems();
		items.add(cartItemFromUser);
		cartRepo.saveAndFlush(customerCart);
		return ResponseEntity.ok(customerCart);
	}

	@DeleteMapping("/cart/{cartItemId}")
	public ResponseEntity<?> removeItemFromCart(@PathVariable Long cartItemId,
			@CurrentSecurityContext(expression = "authentication.name") String userEmail){
		UserModel currentUser = userRepo.findByEmail(userEmail);
		CartModel customerCart = cartRepo.findByCustomer(currentUser);
		List<CartItemModel> items = customerCart.getCartItems();
		items.removeIf(c -> c.getId() == cartItemId);
		cartRepo.saveAndFlush(customerCart);
		return ResponseEntity.ok(customerCart);
	}

}
