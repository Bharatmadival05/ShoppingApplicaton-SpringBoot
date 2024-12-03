package com.myshopping.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "productscategory")
public class ProductCategory {

	@Id
	private int proCatId;
	private String procatName;
	private String proImage;
	
	public int getProCatId() {
		return proCatId;
	}
	public void setProCatId(int proCatId) {
		this.proCatId = proCatId;
	}
	public String getProcatName() {
		return procatName;
	}
	
	public void setProcatName(String procatName) {
		this.procatName = procatName;
	}
	
	public String getProImage() {
		return proImage;
	}
	public void setProImage(String proImage) {
		this.proImage = proImage;
	}
	public ProductCategory() {
		super();
	}
	public ProductCategory(int proCatId, String procatName, String proImage) {
		super();
		this.proCatId = proCatId;
		this.procatName = procatName;
		this.proImage = proImage;
	}
}
