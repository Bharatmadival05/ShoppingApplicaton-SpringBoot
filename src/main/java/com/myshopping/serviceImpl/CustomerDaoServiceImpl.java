package com.myshopping.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myshopping.model.Customer;
import com.myshopping.service.CustomerDaoException;
import com.myshopping.service.CustomerDaoService;
import com.myshopping.util.DButil;

@Service
public class CustomerDaoServiceImpl implements CustomerDaoService {

	ArrayList<Customer> customerList= new ArrayList <Customer>();
	
	private static Connection connection=null;
	
	
	public CustomerDaoServiceImpl() {
		try {
			connection = DButil.getConnection();
			System.out.println("connection: "+connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Customer> getCustomer() {
		customerList.clear();
		String getCustomerQuery="SELECT * FROM customer;";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(getCustomerQuery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Customer cust=new Customer();
				cust.setCustmerid(rs.getInt(1));
				cust.setCustomerName(rs.getString(2));
				cust.setGender(rs.getString(3));
				cust.setContactNo(rs.getLong(4));
				cust.setEmail(rs.getString(5));
				cust.setAddress(rs.getString(6));
				cust.setPincode(rs.getInt(7));
				cust.setUsername(rs.getString(8));
				cust.setPassword(rs.getString(9));
				cust.setCustimgString(rs.getString(10));
				customerList.add(cust);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerList;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		 int custmerid = customer.getCustmerid();
		 String customerName = customer.getCustomerName();
		 String Gender = customer.getGender();
		 long contactNo = customer.getContactNo();
		 String email = customer.getEmail();
		 String address = customer.getAddress();
		 int pincode = customer.getPincode();
		 String username=customer.getUsername();
		 String password=customer.getPassword();
		 String custimgString=customer.getCustimgString();
		 String insertQuery="INSERT INTO customer VALUES("+custmerid+",'"+customerName+"','"+Gender+"',"+contactNo+",'"+email+"','"+address+"',"+pincode+",'"+username+"','"+password+"','"+custimgString+"')";
		 
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(insertQuery);
			stmt.executeUpdate();
			System.out.println("Customer added successfully ");
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
            throw new CustomerDaoException("Error adding customer", e);
		}
}

	@Override
	public boolean customerLoginValidation(String username, String password) {
		System.out.println("username: "+username+"\npassword: "+password);
		boolean flag = false;
		String loginQuery="SELECT * FROM customer WHERE username ='"+username+"' or email= '"+username+"';";
		System.out.println(loginQuery);		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(loginQuery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				
				if((rs.getString(8).equals(username)|| rs.getString(5).equals(username)) && rs.getString(9).equals(password)) {
					flag=true;
					currentCustomer.setCustmerid(rs.getInt(1));
					currentCustomer.setCustomerName(rs.getString(2));
					currentCustomer.setGender(rs.getString(3));
					currentCustomer.setContactNo(rs.getLong(4));
					currentCustomer.setEmail(rs.getString(5));
					currentCustomer.setAddress(rs.getString(6));
					currentCustomer.setPincode(rs.getInt(7));
					currentCustomer.setUsername(rs.getString(8));
					currentCustomer.setPassword(rs.getString(9));
				}
				else {
					System.out.println("Invalid Customer data");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public void updateCustomer(Customer customer) {
		int custmerid = customer.getCustmerid();
		 String customerName = customer.getCustomerName();
		 String gender = customer.getGender();
		 long contactNo = customer.getContactNo();
		 String email = customer.getEmail();
		 String address = customer.getAddress();
		 int pincode = customer.getPincode();
		 String username=customer.getUsername();
		 String password=customer.getPassword();
		 String custimgString=customer.getCustimgString();
		 
		 StringBuilder updateQuery = new StringBuilder("UPDATE customer SET ");
		 	if(custmerid != 0) {
		 		updateQuery.append(" customerid = ").append(custmerid).append(",");
		 	}
		    if (customerName != null) {
		        updateQuery.append(" customerName = '").append(customerName).append("',");
		    }
		    if (gender != null) {
		        updateQuery.append(" Gender = '").append(gender).append("',");
		    }
		    if (contactNo != 0) {
		        updateQuery.append(" contactNo = ").append(contactNo).append(",");
		    }
		    if (email != null) {
		        updateQuery.append(" email = '").append(email).append("',");
		    }
		    if (address != null) {
		        updateQuery.append(" address = '").append(address).append("',");
		    }
		    if (pincode != 0) {
		        updateQuery.append(" pincode = ").append(pincode).append(",");
		    }
		    if (password != null) {
		        updateQuery.append(" password = '").append(password).append("',");
		    }
		    if (custimgString != null) {
		        updateQuery.append(" custimgString = '").append(custimgString).append("',");
		    }

		    // Remove the trailing comma and add the WHERE clause
		    updateQuery.setLength(updateQuery.length() - 1);
		    updateQuery.append(" WHERE username = '").append(username).append("';");
		 
		 PreparedStatement stmt1;
		 try {
			stmt1=connection.prepareStatement(updateQuery.toString());
			int rowsUpdated=stmt1.executeUpdate();
			
			if (rowsUpdated > 0) { // Commit the transaction
		        System.out.println("Student updated successfully");
		    } else {
		        System.out.println("No rows were updated.");
		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	
	@Override
	public void deleteCustomer(String username) {
		String deleteQuery="DELETE FROM customer WHERE username='"+username+"';";
		
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(deleteQuery);
			stmt.executeUpdate();
			System.out.println("Customer data deleted successfully ");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	Customer currentCustomer=new Customer();
	@Override
	public Customer currentcustomerdetail(String username) {
		String getCustomerQuery="SELECT * FROM customer WHERE username='"+username+"' or email= '"+username+"';";
		PreparedStatement stmt;
		
		try {
			stmt=connection.prepareStatement(getCustomerQuery);
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				
				currentCustomer.setCustmerid(rs.getInt(1));
				currentCustomer.setCustomerName(rs.getString(2));
				currentCustomer.setGender(rs.getString(3));
				currentCustomer.setContactNo(rs.getLong(4));
				currentCustomer.setEmail(rs.getString(5));
				currentCustomer.setAddress(rs.getString(6));
				currentCustomer.setPincode(rs.getInt(7));
				currentCustomer.setUsername(rs.getString(8));
				currentCustomer.setPassword(rs.getString(9));
				currentCustomer.setCustimgString(rs.getString(10));
				customerList.add(currentCustomer);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentCustomer;
	}   
}
