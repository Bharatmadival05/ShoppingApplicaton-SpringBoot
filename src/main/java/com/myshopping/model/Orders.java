package com.myshopping.model;

import java.time.LocalDateTime;

public class Orders {

	private int orderid;
	private String customerusername;
	private int productid;
	private int qty;
	private int orderprice;
	private String status;
	private LocalDateTime orderTime;
	
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getCustomerusername() {
		return customerusername;
	}
	public void setCustomerusername(String customerusername) {
		this.customerusername = customerusername;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(int orderprice) {
		this.orderprice = orderprice;
	}
	public Orders(int orderid, String customerusername, int productid, int qty, int orderprice, String status, LocalDateTime orderTime) {
		super();
		this.orderid = orderid;
		this.customerusername = customerusername;
		this.productid = productid;
		this.qty = qty;
		this.orderprice = orderprice;
		this.status = status;
		this.orderTime = orderTime;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}
	
	
}
