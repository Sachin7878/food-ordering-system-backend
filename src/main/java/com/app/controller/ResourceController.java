package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.HotelModel;
import com.app.service.HotelDBService;
import com.app.service.UserDBService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResourceController {

	@Autowired
	private UserDBService userService;
	@Autowired
	private HotelDBService hotelService;

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

		return hotelService.createHotel(incHotel);

	}

}
