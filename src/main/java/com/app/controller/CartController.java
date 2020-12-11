package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CartModel;
import com.app.model.UserModel;
import com.app.repository.CartRepository;
import com.app.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CartController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	CartRepository cartRepo;

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

		System.out.println(customerCart);
		return customerCart;
	}
}
