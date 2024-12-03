package com.myshopping.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Products {

	@Id
	private	int proId;
	private int proCatId;
	private String proName;
	private String proDes;
	private int proPrice;
	private String prodImg;
	
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public int getProCatId() {
		return proCatId;
	}
	public void setProCatId(int proCatId) {
		this.proCatId = proCatId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProDes() {
		return proDes;
	}
	public void setProDes(String proDes) {
		this.proDes = proDes;
	}
	public int getProPrice() {
		return proPrice;
	}
	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}
	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
	public Products(int proId, int proCatId, String proName, String proDes, int proPrice, String prodImg) {
		super();
		this.proId = proId;
		this.proCatId = proCatId;
		this.proName = proName;
		this.proDes = proDes;
		this.proPrice = proPrice;
		this.prodImg = prodImg;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
}
