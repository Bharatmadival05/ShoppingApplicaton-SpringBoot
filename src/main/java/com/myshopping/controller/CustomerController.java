package com.myshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.model.Customer;
import com.myshopping.service.CustomerDaoService;

@CrossOrigin(origins={"http://localhost:4200/","http://localhost:3000/","https://myshoppingbharat.netlify.app/"})
@RestController
public class CustomerController {

	@Autowired
	private CustomerDaoService custService;
	
	@GetMapping("/Customer")
	public List<Customer> getAllCustomer(){
		return custService.getCustomer();
	}
	@PostMapping("/addcustomer")
	public Customer addCustomerData(@RequestBody Customer customer) {
		return custService.addCustomer(customer);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/deletecustomer/{username}")
	public void deleteCustomer(@PathVariable String username) {
		custService.deleteCustomer(username);
	}
	@GetMapping("customer/{username}/{password}")
	public boolean CustomerLogin(@PathVariable String username,@PathVariable String password) {
		return custService.customerLoginValidation(username,password);
	}
	
	@GetMapping("currentcustomer/{username}")
	public Customer customer(@PathVariable String username){
		return custService.currentcustomerdetail(username);
	}
	@RequestMapping(method=RequestMethod.PUT, value="/updatecustomer")
	public void updateCustomer(@RequestBody Customer customer) {
		 custService.updateCustomer(customer);
	}
}