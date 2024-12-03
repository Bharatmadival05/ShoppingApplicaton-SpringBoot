package com.myshopping.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myshopping.model.Orders;
import com.myshopping.service.OrdersDaoService;
import com.myshopping.util.DButil;

@Service
public class OrdersDaoServiceImpl implements OrdersDaoService {

	ArrayList<Orders> orderList=new ArrayList<Orders>();
	
	private static Connection connection=null;
	
	public OrdersDaoServiceImpl(){
		try {
			connection=DButil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Orders> getOrders() {
		orderList.clear();
		String getOrderQuery="SELECT * FROM orders;";
		PreparedStatement stmt;
		
		try {
			stmt=connection.prepareStatement(getOrderQuery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Orders order=new Orders();
				order.setOrderid(rs.getInt(1));
				order.setCustomerusername(rs.getString(2));
				order.setProductid(rs.getInt(3));
				order.setQty(rs.getInt(4));
				order.setOrderprice(rs.getInt(5));
				orderList.add(order);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderList;
	}

	@Override
	public void addOrders(Orders order) {
	    int orderid = order.getOrderid();
	    String customerusername = order.getCustomerusername();
	    int productid = order.getProductid();
	    int qty = order.getQty();
	    int orderprice = order.getOrderprice();
	    String status = order.getStatus();
	    LocalDateTime orderTime = LocalDateTime.now();

	    String insertQuery = "INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
	        stmt.setInt(1, orderid);
	        stmt.setString(2, customerusername);
	        stmt.setInt(3, productid);
	        stmt.setInt(4, qty);
	        stmt.setInt(5, orderprice);
	        stmt.setString(6, status);
	        stmt.setObject(7, Timestamp.valueOf(orderTime));

	        stmt.executeUpdate();
	        System.out.println("Order placed successfully");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void deleteOrder(int orderid) {
		String deleteOrder="DELETE FROM orders WHERE orderid="+orderid+";";
		PreparedStatement stmt;
		
		try {
			stmt=connection.prepareStatement(deleteOrder);
			stmt.executeUpdate();
			System.out.println("Order cancelled Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Orders> getUserorders(String username) {
		orderList.clear();
		String userorderquery="SELECT * FROM orders WHERE customerusername='"+username+"';";
		PreparedStatement stmt;
		
		try {
			stmt=connection.prepareStatement(userorderquery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Orders order=new Orders();
				order.setOrderid(rs.getInt(1));
				order.setCustomerusername(rs.getString(2));
				order.setProductid(rs.getInt(3));
				order.setQty(rs.getInt(4));
				order.setOrderprice(rs.getInt(5));
				order.setStatus(rs.getString(6));
				order.setOrderTime(rs.getObject(7,LocalDateTime.class));
				orderList.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public void updateOrders(int orderid, String status) {
		String updateorder="UPDATE orders SET status = '"+status+"' WHERE orderid="+orderid+";";
		
		PreparedStatement stmt1;
		
		try {
			stmt1=connection.prepareStatement(updateorder);
			stmt1.executeUpdate(updateorder);	
			System.out.println(updateorder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	
}
