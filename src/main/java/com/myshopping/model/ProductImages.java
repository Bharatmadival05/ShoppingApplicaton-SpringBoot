package com.myshopping.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductImages {

	@Id
	private String id;
	private int proId;
	private List<String> base64Photos;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public List<String> getBase64Photos() {
		return base64Photos;
	}
	public void setBase64Photos(List<String> base64Photos) {
		this.base64Photos = base64Photos;
	}
	public ProductImages(String id, int proId, List<String> base64Photos) {
		super();
		this.id = id;
		this.proId = proId;
		this.base64Photos = base64Photos;
	}
	public ProductImages() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
