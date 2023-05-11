package com.fdmgroup.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SC_ORDER")
public class Order {

	@Id
	@Column(name="ORDER_ID", unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderIdSeq")
	@SequenceGenerator(name = "orderIdSeq", sequenceName = "ORDER_ID", initialValue = 20, allocationSize = 1)
	
	private int orderId;
	private LocalDate orderDate; // localdate.now() .of()
	private double price;

	@ManyToOne
	private Customer customer;

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderDate(LocalDate orderDate) {
		// Date date = Date.valueOf(orderDate);
		this.orderDate = orderDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
