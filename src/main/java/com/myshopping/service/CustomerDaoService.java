package com.myshopping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myshopping.model.Customer;

@Service
public interface CustomerDaoService {

	public List<Customer> getCustomer();//Read operation
	public Customer addCustomer(Customer customer);//Create operation
	public void deleteCustomer(String username);//Delete operation
	public void updateCustomer(Customer customer);//Update operation
	public boolean customerLoginValidation(String username, String password);
	public Customer  currentcustomerdetail(String username);
	
}