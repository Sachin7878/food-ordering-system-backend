package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_items")
public class MenuItemList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mId;

	@Column(name = "item_name", nullable = false)
	private String itemName;

	@Column(name = "item_price", nullable = false)
	private double itemPrice;

	private boolean available;

	public MenuItemList() {
		super();
	}

	public MenuItemList(Long mId, String itemName, double itemPrice, boolean available) {
		super();
		this.mId = mId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.available = available;
	}

	public Long getmId() {
		return mId;
	}

	public void setmId(Long mId) {
		this.mId = mId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "MenuItemList [mId=" + mId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", available="
				+ available + "]";
	}

}
