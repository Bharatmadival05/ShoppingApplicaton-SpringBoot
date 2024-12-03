package com.myshopping.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.myshopping.model.ProductCategory;
import com.myshopping.model.Products;
import com.myshopping.service.ProductsDaoService;
import com.myshopping.util.DButil;




@Service
public class ProductsDaoServiceImpl implements ProductsDaoService {

	ArrayList<Products> productList=new ArrayList<Products>();
	ArrayList<ProductCategory> procatList=new ArrayList<ProductCategory>();
	
	private static Connection connection=null;
	
	public ProductsDaoServiceImpl() {
		try {
			connection=DButil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Products> getProducts(){
		productList.clear();
		String getProductQuery="SELECT * FROM products";
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement(getProductQuery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Products prod=new Products();
				prod.setProId(rs.getInt(1));
				prod.setProCatId(rs.getInt(2));
				prod.setProName(rs.getString(3));
				prod.setProDes(rs.getString(4));
				prod.setProPrice(rs.getInt(5));
				prod.setProdImg(rs.getString(6));
				productList.add(prod);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
		
	}

	@Override
	public Products addProducts(Products product) {
		int ProId=product.getProId();
		int ProCatId=product.getProCatId();
		String ProName=product.getProName();
		String ProDes=product.getProDes();
		int ProPrice=product.getProPrice();
		String ProdImg=product.getProdImg();
		
		String insertQuery="INSERT INTO products VALUES("+ProId+","+ProCatId+",'"+ProName+"','"+ProDes+"',"+ProPrice+",'"+ProdImg+"');";
		
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement(insertQuery);
			stmt.executeUpdate();
			System.out.println("Products added Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Duplicate product Id");
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void deleteProducts(int ProId) {
		String deleteQuery="DELETE FROM products WHERE ProId="+ProId+";";
		
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement(deleteQuery);
			stmt.executeUpdate();
			System.out.println("Product deleted successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateProducts(Products product) {
		int ProId=product.getProId();
		int ProCatId=product.getProCatId();
		String ProName=product.getProName();
		String ProDes=product.getProDes();
		int ProPrice=product.getProPrice();
		String ProdImg=product.getProdImg();
		
		StringBuilder updateQuery=new StringBuilder(" UPDATE products SET ");
		if(ProCatId!=0) {
			updateQuery.append(" ProCatId = ").append(ProCatId).append(",");
		}
		if(ProName!=null) {
			updateQuery.append(" ProName = '").append(ProName).append("',");
		}
		if(ProDes!=null) {
			updateQuery.append(" ProDes = '").append(ProDes).append("',");
		}
		if(ProPrice!=0) {
			updateQuery.append(" ProPrice = ").append(ProPrice).append(",");
		}
		if(ProdImg!=null) {
			updateQuery.append(" ProdImg = '").append(ProdImg).append("',");
		}
		
		updateQuery.setLength(updateQuery.length()-1);
		updateQuery.append(" WHERE ProId = '").append(ProId).append("';");
		
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement(updateQuery.toString());
			stmt.executeUpdate();
			System.out.println("Product updated successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// In ProductsDaoServiceImpl.java
	@Override
	public List<Products> getProductsByCategoryId(int categoryId) {
	    List<Products> productsByCategory = new ArrayList<>();
	    // Implement logic to retrieve products by category ID from the database
	    try {
	        String getProductByCategoryQuery = "SELECT * FROM products WHERE ProCatId = ?";
	        PreparedStatement stmt = connection.prepareStatement(getProductByCategoryQuery);
	        stmt.setInt(1, categoryId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Products prod = new Products();
	            prod.setProId(rs.getInt(1));
	            prod.setProCatId(rs.getInt(2));
	            prod.setProName(rs.getString(3));
	            prod.setProDes(rs.getString(4));
	            prod.setProPrice(rs.getInt(5));
	            prod.setProdImg(rs.getString(6));
	            productsByCategory.add(prod);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return productsByCategory;
	}

	@Override
	public int orderprice(int productid) {
		int productprice=0;
		 String productPriceQuery = "SELECT ProPrice FROM products WHERE proId = " + productid;
		 
	        try {
	        	PreparedStatement stmt = connection.prepareStatement(productPriceQuery);
				ResultSet resultSet = stmt.executeQuery(productPriceQuery);
				
				if (resultSet.next()) {
		            productprice = resultSet.getInt("ProPrice");
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     return productprice;
	}

	@Override
	public List<Products> getProductsdetails(int proId) {
		productList.clear();
		String getProductQuery="SELECT * FROM products WHERE ProId="+proId+";";
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement(getProductQuery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Products prod=new Products();
				prod.setProId(rs.getInt(1));
				prod.setProCatId(rs.getInt(2));
				prod.setProName(rs.getString(3));
				prod.setProDes(rs.getString(4));
				prod.setProPrice(rs.getInt(5));
				prod.setProdImg(rs.getString(6));
				productList.add(prod);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<ProductCategory> getCategory() {
		procatList.clear();
		String getProductCatQuery="SELECT * FROM productcategory";
		PreparedStatement stmt;
		
		try {
			stmt=connection.prepareStatement(getProductCatQuery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				ProductCategory procat= new ProductCategory();
				procat.setProCatId(rs.getInt(1));
				procat.setProcatName(rs.getString(2));
				procat.setProImage(rs.getString(3));
				procatList.add(procat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return procatList;
	}

	
	@Override
	public List<Products> searchProducts(String search) {
		productList.clear();
		String getProductQuery="SELECT * FROM products where ProDes like '%"+search+"%' or ProName like '%"+search+"%';";
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement(getProductQuery);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Products prod=new Products();
				prod.setProId(rs.getInt(1));
				prod.setProCatId(rs.getInt(2));
				prod.setProName(rs.getString(3));
				prod.setProDes(rs.getString(4));
				prod.setProPrice(rs.getInt(5));
				prod.setProdImg(rs.getString(6));
				productList.add(prod);
			}
		} catch (SQLException e) {
			// TODO  Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}


	@Override
	public String addCategory(ProductCategory category) {
        int proCatId = category.getProCatId();
        String procatName = category.getProcatName();
        String proImage = category.getProImage();

        if (proCatId == 0 || procatName == null || proImage == null) {
            return "{\"status\":\"error\",\"message\":\"Product category fields must not be null\"}";
        }

        String insertQuery = "INSERT INTO productcategory (proCatId, procatName, proImage) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setInt(1, proCatId);
            stmt.setString(2, procatName);
            stmt.setString(3, proImage);
            stmt.executeUpdate();
            System.out.println(insertQuery);
            System.out.println("Category added successfully");
            return "{\"status\":\"success\",\"message\":\"Category added successfully\"}";
        } catch (SQLException e) {
            e.printStackTrace();
            return "{\"status\":\"error\",\"message\":\"Database error occurred\"}";
        }
    }


	@Override
	public Products getProductsdetail(int proId) {
		Products prod = null;  // Initialize a single Products object
	    String getProductQuery = "SELECT * FROM products WHERE ProId = ?;";
	    PreparedStatement stmt;
	    try {
	        stmt = connection.prepareStatement(getProductQuery);
	        stmt.setInt(1, proId);  // Use a prepared statement to prevent SQL injection
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            prod = new Products();
	            prod.setProId(rs.getInt(1));
	            prod.setProCatId(rs.getInt(2));
	            prod.setProName(rs.getString(3));
	            prod.setProDes(rs.getString(4));
	            prod.setProPrice(rs.getInt(5));
	            prod.setProdImg(rs.getString(6));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return prod;
}
}
