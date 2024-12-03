package com.myshopping.serviceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myshopping.model.Admin;
import com.myshopping.model.Orders;
import com.myshopping.service.AdminDaoService;
import com.myshopping.util.DButil;

@Service
public class AdminDaoServiceImpl implements AdminDaoService{
	
	ArrayList<Orders> orderList=new ArrayList<Orders>();

	private static Connection connection;
	
	Admin currentAdmin=new Admin();
	
	public AdminDaoServiceImpl() {
		try {
			connection=DButil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean adminLoginValidation(String username,String password) {
		System.out.println("Username: "+username+"\nPassword: "+password);
		boolean flag=false;
		
		String loginQuery="SELECT * FROM admin WHERE username='"+username+"';";
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement(loginQuery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
					flag=true;
					currentAdmin.setUsername(rs.getString(1));
					currentAdmin.setPassword(rs.getString(2));
				}
				else
				{
					System.out.println("Invalid customer details");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
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
	public void deleteOrders(int orderid) {
		String deleteQuery="DELETE FROM orders WHERE orderid="+orderid+";";
		
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement(deleteQuery);
			stmt.executeUpdate();
			System.out.println("Order deleted successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
