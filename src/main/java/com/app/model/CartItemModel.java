package com.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cart_items")
public class CartItemModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(targetEntity = MenuItemModel.class)
	private MenuItemModel item;

	private int quantity;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "hotel_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private CartModel cart;

	public CartItemModel() {
		super();
	}

	public CartItemModel(Long id, MenuItemModel item, int quantity, CartModel cart) {
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

	public CartModel getCart() {
		return cart;
	}

	public void setCart(CartModel cart) {
		this.cart = cart;
	}

}
