package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItemModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(targetEntity = MenuItemModel.class)
	private MenuItemModel item;

	private int quantity;


	public CartItemModel() {
		super();
	}

	public CartItemModel(Long id, MenuItemModel item, int quantity) {
		super();
		this.id = id;
		this.item = item;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MenuItemModel getItem() {
		return item;
	}

	public void setItem(MenuItemModel item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItemModel [id=" + id + ", item=" + item + ", quantity=" + quantity + "]";
	}

}
