package com.myshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.model.ProductCategory;
import com.myshopping.productservice.ProductCategoryService;
import com.myshopping.service.ProductsDaoService;

@CrossOrigin(origins={"http://localhost:4200/","http://localhost:4300/"})
@RestController
public class ProCatController {

private final ProductCategoryService procatservice;
	
	@Autowired
	private ProductsDaoService prodService;
	
	public ProCatController(ProductCategoryService procatservice) {
		this.procatservice=procatservice;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/savecat")
    public ProductCategory savecat(@RequestBody ProductCategory procat) {
        if (procat.getProCatId() == 0 || procat.getProcatName() == null || procat.getProImage() == null) {
            throw new IllegalArgumentException("Product category fields must not be null");
        }
        return procatservice.saveCat(procat);
    }
	
	@PostMapping("/addcat")
	public ResponseEntity<String> addCategory(@RequestBody ProductCategory category) {
        String response = prodService.addCategory(category);
        if (response.contains("\"status\":\"error\"")) {
            return ResponseEntity.badRequest().body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
