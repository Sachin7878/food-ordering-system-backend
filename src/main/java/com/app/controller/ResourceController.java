package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.HotelModel;
import com.app.model.MenuItemList;
import com.app.service.HotelDBService;
import com.app.service.MenuItemsDBService;
import com.app.service.UserDBService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResourceController {

	@Autowired
	private UserDBService userService;
	@Autowired
	private HotelDBService hotelService;
	
	@Autowired
	private MenuItemsDBService menuItemService;

	@RequestMapping("/hellouser")
	public String getUser() {
		return "Hello user";
	}

	@RequestMapping("/helloadmin")
	public String getAdmin() {
		return "Hello Admin";
	}

	@DeleteMapping("/deleteUser/{email}")
	public String deleteUser(@PathVariable String email) {
		return userService.deleteUser(email);
	}
	
	@PostMapping("/api/createhotel")
	public ResponseEntity<Object> createHotel(@RequestBody HotelModel incHotel) {
		System.out.println(incHotel);
		return hotelService.createHotel(incHotel);

	}
	
	@GetMapping("/api/gethotels")
	public ResponseEntity<List<HotelModel>> getHotels() {
		
		return hotelService.fetchAllHotels();
	}
	
	@GetMapping("/api/getmenu/{hotelId}")
	public ResponseEntity<List<MenuItemList>> getMenu(@PathVariable Long hotelId) {
		System.out.println(hotelId + " in resource controller getMenu method");
		return menuItemService.fetchAllMenuItemsByHotel(hotelId);
	}
	
	@GetMapping("/api/gethotelId/{hotelId}")
	public ResponseEntity<HotelModel> gethotelById(@PathVariable Long hotelId) {
		System.out.println(hotelId + " in resource controller gethotelById method");
		return hotelService.getHotelById(hotelId);
	}

}
