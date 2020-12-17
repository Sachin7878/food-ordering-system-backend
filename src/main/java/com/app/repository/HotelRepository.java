package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.HotelModel;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {
	HotelModel findByMobileNo(String mobileNo);
}