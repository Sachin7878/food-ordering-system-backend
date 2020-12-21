package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.CartItemModel;
import com.app.entities.CartModel;
import com.app.entities.HotelModel;
import com.app.entities.OrderItemModel;
import com.app.entities.OrderModel;
import com.app.entities.UserModel;
import com.app.repository.CartRepository;
import com.app.repository.OrderItemRepository;
import com.app.repository.OrderRepository;
import com.app.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OrderController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderItemRepository orderItemRepo;

//	@Autowired
//	private CartItemRepository cartItemRepo;
	
	@PostMapping("/order/place")
	public ResponseEntity<?> placeOrder(@CurrentSecurityContext(expression = "authentication.name") String userEmail){
		
		UserModel currentUser = userRepo.findByEmail(userEmail);
		CartModel customerCart = cartRepo.findByCustomer(currentUser);
		List<CartItemModel> items = customerCart.getCartItems();
		List<OrderItemModel> orderItems = new ArrayList<>();
		HotelModel itemHotel = items.get(0).getItem().getHotel();
		System.out.println(itemHotel.toString());
		
		items.forEach(i -> {
			OrderItemModel newOrderItem = new OrderItemModel();
			newOrderItem.setItem(i.getItem());
			newOrderItem.setQuantity(i.getQuantity());
			System.out.println(newOrderItem.toString());
			orderItems.add(newOrderItem);
			orderItemRepo.saveAndFlush(newOrderItem);
		});
		
		System.out.println("after loop");
		OrderModel newOrder = new OrderModel();
		newOrder.setCustomer(currentUser);
		newOrder.setHotel(itemHotel);
		newOrder.setOrderItems(orderItems);
		
		System.out.println("after new order");
		System.out.println(newOrder.toString());

		orderRepo.saveAndFlush(newOrder);
		
		items.removeAll(items);
		cartRepo.saveAndFlush(customerCart);
		
		return ResponseEntity.ok().build();
	}
	
}
