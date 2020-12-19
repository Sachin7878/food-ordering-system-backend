package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cust_excs.ResourceNotFoundException;
import com.app.entities.AddressModel;
import com.app.entities.HotelModel;
import com.app.entities.MenuItemModel;
import com.app.entities.UserModel;
import com.app.repository.AddressRepository;
import com.app.repository.HotelRepository;
import com.app.repository.MenuItemRepository;
import com.app.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	

	@GetMapping()
	public ResponseEntity<?> getHotelDetails(
			@CurrentSecurityContext(expression = "authentication.name") String vendorEmail) {
		HotelModel vendorHotel = getHotelByVendorEmail(vendorEmail);
		return ResponseEntity.ok(vendorHotel);
	}
	
	@GetMapping("/hotel/menu")
	public ResponseEntity<?> getHotelMenuDetails(
			@CurrentSecurityContext(expression = "authentication.name") String vendorEmail, Pageable pageable) {
		HotelModel vendorHotel = getHotelByVendorEmail(vendorEmail);
		menuItemRepository.findByHotelId(vendorHotel.getId(), pageable);
		return ResponseEntity.ok(menuItemRepository.findByHotelId(vendorHotel.getId(), pageable));
	}

	@PutMapping("/hotel")
	public ResponseEntity<?> updateHotelDetails(@Valid @RequestBody HotelModel hotelRequest,
			@CurrentSecurityContext(expression = "authentication.name") String vendorEmail) {

		HotelModel vendorHotel = getHotelByVendorEmail(vendorEmail);

		vendorHotel.setHotelName(hotelRequest.getHotelName());
		vendorHotel.setMobileNo(hotelRequest.getMobileNo());
		AddressModel reqAdd = hotelRequest.getAddress();

		if (addressRepo.existsById(reqAdd.getId())) {
			addressRepo.findById(reqAdd.getId()).map(hotelAdd -> {
				hotelAdd.setAddressLine1(reqAdd.getAddressLine1());
				hotelAdd.setAddressLine2(reqAdd.getAddressLine2());
				hotelAdd.setPincode(reqAdd.getPincode());
				hotelAdd.setCity(reqAdd.getCity());
				hotelAdd.setState(reqAdd.getState());
				hotelAdd.setCountry(reqAdd.getCountry());
				return addressRepo.save(hotelAdd);
			});
		} else {
			vendorHotel.setAddress(reqAdd);
		}

		hotelRepo.saveAndFlush(vendorHotel);

		return ResponseEntity.ok(vendorHotel);
	}
	
	
	@PutMapping("/hotels/menu/{menuId}")
	public MenuItemModel updateMenuItem(
			@PathVariable(value = "menuId") Long menuId, @Valid @RequestBody MenuItemModel menuRequest) {

		return menuItemRepository.findById(menuId).map(menu -> {
			menu.setItemName(menuRequest.getItemName());
			menu.setItemPrice(menuRequest.getItemPrice());
			menu.setAvailable(menuRequest.isAvailable());
			return menuItemRepository.save(menu);
		}).orElseThrow(() -> new ResourceNotFoundException("menuId " + menuId + "not found"));
	}
	
	@DeleteMapping("/hotel/menu/{menuId}")
	public ResponseEntity<?> deleteComment(
			@PathVariable(value = "menuId") Long menuId) {
		return menuItemRepository.findById(menuId).map(menu -> {
			menuItemRepository.delete(menu);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Menu Item not found with id " + menuId));
	}
	
	private HotelModel getHotelByVendorEmail(String vendorEmail) {
		UserModel currentUser = userRepo.findByEmail(vendorEmail);
		return hotelRepo.findByVendor(currentUser);
	}

}
