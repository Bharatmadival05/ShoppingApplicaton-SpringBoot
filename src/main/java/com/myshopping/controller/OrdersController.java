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

import com.myshopping.model.Orders;
import com.myshopping.service.OrdersDaoService;

@CrossOrigin(origins={"http://localhost:4200/","http://localhost:3000/","https://myshoppingbharat.netlify.app/"})
@RestController
public class OrdersController {

	@Autowired
	private OrdersDaoService orders;
	
	@GetMapping("/getOrders")
	public List<Orders> getOrders(){
		return orders.getOrders();
	}
	
	@PostMapping("/addOrders")
	public void addOrders(@RequestBody Orders order) {
		orders.addOrders(order);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="orderdelete/{orderid}")
	public void deleteOrder(@PathVariable int orderid) {
		orders.deleteOrder(orderid);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="userorders/{username}")
	public List<Orders> getUserorders(@PathVariable String username){
		return orders.getUserorders(username);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="updateorder/{orderid}/{status}")
	public void updateOrders(@PathVariable int orderid,@PathVariable String status) {
		orders.updateOrders(orderid, status);
	}
}
