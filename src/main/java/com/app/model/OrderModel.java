package com.app.model;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(targetEntity = CartModel.class, cascade = CascadeType.ALL)
	private CartModel cart;
	
	private int tax;
	
	private double order_total;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserModel customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CartModel getCart() {
		return cart;
	}

	public void setCart(CartModel cart) {
		this.cart = cart;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public double getOrder_total() {
		return order_total;
	}

	public void setOrder_total(double order_total) {
		this.order_total = order_total;
	}

	public UserModel getCustomer() {
		return customer;
	}

	public void setCustomer(UserModel customer) {
		this.customer = customer;
	}
	
}
