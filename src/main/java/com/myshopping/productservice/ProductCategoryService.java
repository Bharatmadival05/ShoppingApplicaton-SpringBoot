package com.myshopping.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshopping.model.ProductCategory;
import com.myshopping.repository.ProductsCategory;

@Service
public class ProductCategoryService {

	private final com.myshopping.repository.ProductsCategory procat;
	
	@Autowired
	public ProductCategoryService(ProductsCategory productcategory){
		this.procat = productcategory;
	}
	
	public ProductCategory saveCat(ProductCategory procat) {
        if (procat.getProCatId() == 0 || procat.getProcatName() == null || procat.getProImage() == null) {
            throw new IllegalArgumentException("Product category fields must not be null");
        }
        return this.procat.save(procat);
    }
}
