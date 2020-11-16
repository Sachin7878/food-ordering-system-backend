package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.HotelModel;
import com.app.model.MenuItemList;
import com.app.repository.HotelRepository;

@Service
public class MenuItemsDBService {
	
	@Autowired
	private HotelRepository repo;
//
//	@Transactional
//	public ResponseEntity<List<MenuItemList>> fetchAllMenuItemsByHotel(Long hotelId) {
//		System.out.println(hotelId);
//		HotelModel getHotel = repo.findById(hotelId).get();
//		System.out.println(getHotel);
//		List<MenuItemList> menuItems = getHotel.getMenuItems();
//		System.out.println(menuItems);
//		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(menuItems);
//	}
}
