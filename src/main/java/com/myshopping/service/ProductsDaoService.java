package com.myshopping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myshopping.model.ProductCategory;
import com.myshopping.model.Products;



@Service
public interface ProductsDaoService {

	public List<Products> getProducts();//Read Operation
	public Products addProducts(Products product);//Create Operation
	public void deleteProducts(int ProId);//Delete Operation
	public void updateProducts(Products product);//Update operation
	public List<Products> getProductsByCategoryId(int categoryId);
	public int orderprice(int productid);
	public List<Products> getProductsdetails(int proId);
	public List<ProductCategory> getCategory();
	public List<Products> searchProducts(String search);
	public String addCategory(ProductCategory category);
	public Products getProductsdetail(int proId);

}
