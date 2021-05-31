package com.infiniteskills.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String itemId;
	private double itemTotal;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="cart_idd", nullable=false)
	private Cart cart;
	
	//Hibernate requires no-args constructor
	public Item(){}
	
	public Item(String itemId, double total, int qty, Cart c){
		this.itemId=itemId;
		this.itemTotal=total;
		this.quantity=qty;
		this.cart=c;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart1() {
		return cart;
	}

	public void setCart1(Cart cart1) {
		this.cart = cart1;
	}
}