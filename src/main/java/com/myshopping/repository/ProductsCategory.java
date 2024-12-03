package com.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.model.ProductCategory;

public interface ProductsCategory extends MongoRepository<ProductCategory, Integer>{

}
