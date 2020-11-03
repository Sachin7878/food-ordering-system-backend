package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.HotelModel;
import com.app.repository.HotelRepository;

@Service
public class HotelDBService {
	@Autowired
	private HotelRepository repo;

	@Transactional
	public ResponseEntity<Object> createHotelWithoutAddress(HotelModel tempHotel) {
		HotelModel newHotel = new HotelModel();
		newHotel.setHotelName(tempHotel.getHotelName());
		newHotel.setMobileNo(tempHotel.getMobileNo());
		HotelModel savedHotel = repo.save(newHotel);
		if (repo.findById(savedHotel.getId()).isPresent()) {
			return ResponseEntity.ok().body("Hotel created successfully.");
		} else
			return ResponseEntity.unprocessableEntity().body("Failed to create the hotel specified.");
	}
}
