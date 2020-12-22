package com.app.dto;

import com.app.entities.HotelModel;

public class HotelDto {
	private Long hotelId;
	private String hotelName;
	private String mobileNo;

	public HotelDto(HotelModel hotel) {
		super();
		this.hotelId = hotel.getId();
		this.hotelName = hotel.getHotelName();
		this.mobileNo = hotel.getMobileNo();
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "HotelDto [hotelId=" + hotelId + ", hotelName=" + hotelName + ", mobileNo=" + mobileNo + "]";
	}

}
