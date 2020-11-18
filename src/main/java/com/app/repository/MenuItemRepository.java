package com.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.MenuItemList;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemList, Long> {
	Page<MenuItemList> findByHotelId(Long hotelId, Pageable pageable);
	Optional<MenuItemList> findByIdAndHotelId(Long id, Long hotelId);
}
