package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
			customerCart.setCart_total(0);
			customerCart.setDiscount(0);
			cartRepo.save(customerCart);
		}
		return customerCart;
	}

	@PostMapping("/cart")
	public CartItemModel addItemToCart(@RequestBody CartItemModel cartItemFromUser,
			@CurrentSecurityContext(expression = "authentication.name") String userEmail) {
		UserModel currentUser = userRepo.findByEmail(userEmail);
		CartModel customerCart = cartRepo.findByCustomer(currentUser);
		cartItemFromUser.setCart(customerCart);
		return cartItemRepo.save(cartItemFromUser);
	}
	
	@GetMapping("/cart/items")
	public List<CartItemModel> getCartItems(@CurrentSecurityContext(expression = "authentication.name") String userEmail) {
		UserModel currentUser = userRepo.findByEmail(userEmail);
		CartModel customerCart = cartRepo.findByCustomer(currentUser);
		return cartItemRepo.findByCartId(customerCart.getId());
	}

}
