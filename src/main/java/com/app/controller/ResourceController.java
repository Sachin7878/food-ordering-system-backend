package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cust_excs.ResourceNotFoundException;
import com.app.model.HotelModel;
import com.app.model.MenuItemList;
import com.app.repository.HotelRepository;
import com.app.repository.MenuItemRepository;
import com.app.service.HotelDBService;
import com.app.service.MenuItemsDBService;
import com.app.service.UserDBService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResourceController {

//	@Autowired
//	private UserDBService userService;
//	@Autowired
//	private HotelDBService hotelService;
//
//	@Autowired
//	private MenuItemsDBService menuItemService;

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
    private MenuItemRepository menuItemRepository;

	@RequestMapping("/hellouser")
	public String getUser() {
		return "Hello user";
	}

	@RequestMapping("/helloadmin")
	public String getAdmin() {
		return "Hello Admin";
	}

//	@DeleteMapping("/deleteUser/{email}")
//	public String deleteUser(@PathVariable String email) {
//		return userService.deleteUser(email);
//	}
//	
//	@PostMapping("/api/createhotel")
//	public ResponseEntity<Object> createHotel(@RequestBody HotelModel incHotel) {
//		System.out.println(incHotel);
//		return hotelService.createHotel(incHotel);
//
//	}
//	
//	@GetMapping("/api/gethotels")
//	public ResponseEntity<List<HotelModel>> getHotels() {
//		
//		return hotelService.fetchAllHotels();
//	}
//	
//	@GetMapping("/api/getmenu/{hotelId}")
//	public ResponseEntity<List<MenuItemList>> getMenu(@PathVariable Long hotelId) {
//		System.out.println(hotelId + " in resource controller getMenu method");
//		return menuItemService.fetchAllMenuItemsByHotel(hotelId);
//	}
//	
//	@GetMapping("/api/gethotelId/{hotelId}")
//	public ResponseEntity<HotelModel> gethotelById(@PathVariable Long hotelId) {
//		System.out.println(hotelId + " in resource controller gethotelById method");
//		return hotelService.getHotelById(hotelId);
//	}

	// testing lazy routes
	// dont touch above routes

	@GetMapping("/hotels")
	public Page<HotelModel> getAllHotels(Pageable pageable) {
		return hotelRepository.findAll(pageable);
	}

	@PostMapping("/hotels")
	public HotelModel createHotel(@Valid @RequestBody HotelModel hotel) {
		return hotelRepository.save(hotel);
	}

	@PutMapping("/hotels/{hotelId}")
	public HotelModel updatePost(@PathVariable Long hotelId, @Valid @RequestBody HotelModel hotelRequest) {
		return hotelRepository.findById(hotelId).map(hotel -> {
			hotel.setHotelName(hotelRequest.getHotelName());
			hotel.setMobileNo(hotelRequest.getMobileNo());
			return hotelRepository.save(hotel);
		}).orElseThrow(() -> new ResourceNotFoundException("HotelId " + hotelId + " not found"));
	}

	@DeleteMapping("/hotels/{hotelId}")
	public ResponseEntity<?> deletePost(@PathVariable Long hotelId) {
		return hotelRepository.findById(hotelId).map(hotel -> {
			hotelRepository.delete(hotel);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("HotelId " + hotelId + " not found"));
	}
	
	
	@GetMapping("/hotels/{hotelId}/menu")
    public Page<MenuItemList> getAllMenuItemsByHotelId(@PathVariable (value = "hotelId") Long hotelId,
                                                Pageable pageable) {
        return menuItemRepository.findByHotelId(hotelId, pageable);
    }

    @PostMapping("/hotels/{hotelId}/menu")
    public MenuItemList createMenuItem(@PathVariable (value = "hotelId") Long hotelId,
                                 @Valid @RequestBody MenuItemList menuItem) {
        return hotelRepository.findById(hotelId).map(hotel -> {
        	menuItem.setHotel(hotel);
            return menuItemRepository.save(menuItem);
        }).orElseThrow(() -> new ResourceNotFoundException("HotelId " + hotelId + " not found"));
    }

    @PutMapping("/hotels/{hotelId}/menu/{menuId}")
    public MenuItemList updateMenuItem(@PathVariable (value = "hotelId") Long hotelId,
                                 @PathVariable (value = "menuId") Long menuId,
                                 @Valid @RequestBody MenuItemList menuRequest) {
        if(!hotelRepository.existsById(hotelId)) {
            throw new ResourceNotFoundException("HotelId " + hotelId + " not found");
        }

        return menuItemRepository.findById(menuId).map(menu -> {
            menu.setItemName(menuRequest.getItemName());
            menu.setItemPrice(menuRequest.getItemPrice());
            menu.setAvailable(menuRequest.isAvailable());
            return menuItemRepository.save(menu);
        }).orElseThrow(() -> new ResourceNotFoundException("menuId " + menuId + "not found"));
    }

    @DeleteMapping("/hotels/{hotelId}/menu/{menuId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "hotelId") Long hotelId,
            @PathVariable (value = "menuId") Long menuId) {
        return menuItemRepository.findByIdAndHotelId(menuId, hotelId).map(menu -> {
        	menuItemRepository.delete(menu);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Menu Item not found with id " + menuId + " and hotelId " + hotelId));
    }

}
