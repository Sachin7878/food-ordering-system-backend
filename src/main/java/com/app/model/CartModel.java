package com.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(targetEntity = UserModel.class, cascade = CascadeType.ALL)
	private UserModel customer;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private Set<CartItemModel> cartItems;

	private double cart_total;

	private int discount;

	public CartModel() {
		super();
	}

	public CartModel(double cart_total, int discount, UserModel customer) {
		super();
		this.cart_total = cart_total;
		this.discount = discount;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCart_total() {
		return cart_total;
	}

	public void setCart_total(double cart_total) {
		this.cart_total = cart_total;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public UserModel getCustomer() {
		return customer;
	}

	public void setCustomer(UserModel customer) {
		this.customer = customer;
	}

	public Set<CartItemModel> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItemModel> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "CartModel [id=" + id + ", customer=" + customer + ", cartItems=" + cartItems + ", cart_total="
				+ cart_total + ", discount=" + discount + "]";
	}

}
