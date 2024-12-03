package com.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.model.ProductImages;

public interface ProductRepository extends MongoRepository<ProductImages, Integer> {
	ProductImages findByProId(int proId);
}

