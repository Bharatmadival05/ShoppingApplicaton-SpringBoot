package com.myshopping.service;

import java.util.List;

import com.myshopping.model.Orders;

public interface AdminDaoService {

	public boolean adminLoginValidation(String username,String password);
	public List<Orders> getOrders();
	public void deleteOrders(int orderid);
}
