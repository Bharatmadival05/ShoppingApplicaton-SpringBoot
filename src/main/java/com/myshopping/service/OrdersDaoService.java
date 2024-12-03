package com.myshopping.service;

import java.util.List;

import com.myshopping.model.Orders;

public interface OrdersDaoService {

	public List<Orders> getOrders();
	public void addOrders(Orders order);
	public void deleteOrder(int orderid);
	public List<Orders> getUserorders(String username);
	public void updateOrders(int orderid, String status);
}
