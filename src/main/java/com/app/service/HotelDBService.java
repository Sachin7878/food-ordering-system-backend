package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.AddressModel;
import com.app.model.HotelModel;
import com.app.repository.HotelRepository;

@Service
public class HotelDBService {
	@Autowired
	private HotelRepository repo;

	@Transactional
	public ResponseEntity<Object> createHotel(HotelModel tempHotel) {
		HotelModel newHotel = new HotelModel();
		newHotel.setHotelName(tempHotel.getHotelName());
		newHotel.setMobileNo(tempHotel.getMobileNo());
		AddressModel tempAddress = tempHotel.getAddress();
		if(tempAddress != null) {
			newHotel.setAddress(tempAddress);
		}
		HotelModel savedHotel = repo.save(newHotel);
		if (repo.findById(savedHotel.getId()).isPresent()) {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(savedHotel);
		} else
			return ResponseEntity.unprocessableEntity().body("Failed to create the hotel specified.");
	}
	
	@Transactional
	public ResponseEntity<List<HotelModel>> fetchAllHotels() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(repo.findAll());
	}
	
}
