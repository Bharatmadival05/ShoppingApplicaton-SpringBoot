package com.myshopping.model;


public class Customer {


	private int custmerid;
	private String customerName;
	private String gender;
	private long contactNo;
	private String email;
	private String address;
	private int pincode;
	private String username;
	private String password;
	
	private byte[] custimg;
	private String custimgString;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCustmerid() {
		return custmerid;
	}
	public void setCustmerid(int custmerid) {
		this.custmerid = custmerid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	public Customer(int custmerid, String customerName, String gender, long contactNo, String email, String address,
			int pincode, String username, String password, byte[] custimg, String custimgString) {
		super();
		this.custmerid = custmerid;
		this.customerName = customerName;
		this.gender = gender;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
		this.pincode = pincode;
		this.username = username;
		this.password = password;
		this.custimg = custimg;
		this.custimgString=custimgString;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public byte[] getCustimg() {
		return custimg;
	}
	public void setCustimg(byte[] custimg) {
		this.custimg = custimg;
	}
	public String getCustimgString() {
		return custimgString;
	}
	public void setCustimgString(String custimgString) {
		this.custimgString = custimgString;
	}
	
}
