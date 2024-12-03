package com.myshopping.productservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.myshopping.model.ProductImages;
import com.myshopping.repository.ProductRepository;


@Service
public class ProductService {

	private final com.myshopping.repository.ProductRepository productRepository;
	@SuppressWarnings("unused")
	private final MongoTemplate mongoTemplate;
	
	@Autowired
    public ProductService(ProductRepository productRepository,MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.mongoTemplate=mongoTemplate;
    }
	
	
	public ProductImages saveProductPhotos(int proId, List<String> base64Photos) {
        ProductImages product = productRepository.findByProId(proId);
        if (product == null) {
            product = new ProductImages();
            product.setProId(proId);
        }
        product.setBase64Photos(base64Photos);
        return productRepository.save(product);
    }
	
	public ProductImages getImages(int proId) {
		return productRepository.findByProId(proId);
	}

	
}
