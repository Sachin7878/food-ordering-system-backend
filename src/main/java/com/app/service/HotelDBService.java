package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.cust_excs.ResourceNotFoundException;
import com.app.entities.AddressModel;
import com.app.entities.HotelModel;
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
//		newHotel.setMenuItems(tempHotel.getMenuItems());
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
	
	@Transactional
	public ResponseEntity<HotelModel> getHotelById(Long id) {
		Optional<HotelModel> hotelById = repo.findById(id);
		if(hotelById.isPresent()) {
			HotelModel presentHotel = hotelById.get();
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(presentHotel);
		} else {
			throw new ResourceNotFoundException("Hotel by supplied ID not found");
		}
	}
	
}
