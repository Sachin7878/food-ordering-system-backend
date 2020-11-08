package com.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hotels")
public class HotelModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "hotel_name", nullable = false)
	private String hotelName;

	@Column(nullable = false, name = "mobile_no", unique = true, length = 10)
	private String mobileNo;

	@OneToMany(targetEntity = MenuItemList.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MenuItemList> menuItems;

	@OneToOne(targetEntity = AddressModel.class, cascade = CascadeType.ALL)
	private AddressModel address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public List<MenuItemList> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItemList> menuItems) {
		this.menuItems = menuItems;
	}

	public void addItems(MenuItemList tempItems) {
		if (menuItems == null) {
			menuItems = new ArrayList<>();
		}
		menuItems.add(tempItems);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelModel other = (HotelModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HotelModel [id=" + id + ", hotelName=" + hotelName + ", mobileNo=" + mobileNo + ", menuItems="
				+ menuItems + ", address=" + address + "]";
	}

}
