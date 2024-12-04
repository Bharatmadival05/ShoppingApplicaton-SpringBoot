package com.myshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.model.ProductCategory;
import com.myshopping.model.ProductImages;
import com.myshopping.model.Products;

import com.myshopping.productservice.ProductService;
import com.myshopping.service.ProductsDaoService;


@CrossOrigin(origins={"http://localhost:4200/","http://localhost:3000/","https://papaya-peony-70af71.netlify.app/"})
@RestController
public class ProductsController {

	@Autowired
	private ProductsDaoService prodService;
	
	private final ProductService productsService;

    public ProductsController(ProductService productsService) {
        this.productsService = productsService;
    }
    
	@GetMapping("/getProducts")
	public List<Products> getProducts(){
		return prodService.getProducts();
		
	}
	@PostMapping("/addProducts")
	public void addProducts(@RequestBody Products product) {
		prodService.addProducts(product);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteProducts/{ProId}")
	public void deleteProducts(@PathVariable int ProId) {
		prodService.deleteProducts(ProId);
	}
	
	@PutMapping("/updateProducts")
	public void updateProducts(@RequestBody Products product) {
		prodService.updateProducts(product);
	}
	
	@GetMapping("/getProductsByCategory/{categoryId}")
	public List<Products> getProductsByCategory(@PathVariable int categoryId) {
	    return prodService.getProductsByCategoryId(categoryId);
	}
	
	@GetMapping("/productprice/{productid}")
	public int orderprice(@PathVariable int productid) {
		return prodService.orderprice(productid);
	}
	
	@GetMapping("/productdetails/{proId}")
	public List<Products> getProductsdetails(@PathVariable int proId){
		return prodService.getProductsdetails(proId);
	}
	
	
	@GetMapping("/productcategory")
	public List<ProductCategory> getCategory(){
		return prodService.getCategory();
	}
	
	@GetMapping("/search/{search}")
	public List<Products> searchProducts(@PathVariable String search){
		return prodService.searchProducts(search);
	}
	
	
	@PostMapping("/uploadPhotos")
	public ProductImages uploadPhotos(@RequestBody ProductImages request) {
        return productsService.saveProductPhotos(request.getProId(),request.getBase64Photos());
    }
	
	@GetMapping("/getPhotos/{proId}")
	public ProductImages getImages(@PathVariable int proId){
		return productsService.getImages(proId);
	}
	 	
	@GetMapping("/productdetail/{proId}")
	public Products getProductsdetail(@PathVariable int proId){
		return prodService.getProductsdetail(proId);
	}
}
