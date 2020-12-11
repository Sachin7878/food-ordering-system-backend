package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItemModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(targetEntity = MenuItemList.class)
	private MenuItemList item;

	private int quantity;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cart_id", nullable = false)
	private CartModel cart;

	public CartItemModel() {
		super();
	}

	public CartItemModel(Long id, MenuItemList item, int quantity, CartModel cart) {
		super();
		this.id = id;
		this.item = item;
		this.quantity = quantity;
		this.cart = cart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MenuItemList getItem() {
		return item;
	}

	public void setItem(MenuItemList item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartModel getCart() {
		return cart;
	}

	public void setCart(CartModel cart) {
		this.cart = cart;
	}

}
