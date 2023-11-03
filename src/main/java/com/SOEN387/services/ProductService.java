package com.SOEN387.services;


import java.util.List;

import com.SOEN387.DAOs.ProductDAO;
import com.SOEN387.models.Product;

	
public class ProductService {
	
	
        private ProductDAO  productDAO;
	
	    public ProductService() {
		        productDAO = new ProductDAO();
     	}
	    public List<Product> getAllProducts() {
	        return productDAO.getAllProducts();
	    }

	    public Product getProductByUrlSlug(String urlSlug) {
	        return productDAO.getProductByUrlSlug(urlSlug);
	    }

	    public void addProduct(String sku, String name) {
	        Product product = new Product(name, "", "", "", sku, 0.0, "");
	        productDAO.createProduct(product);
	    }

	    public void updateProduct(String sku,  String name, String description, double price, String vendor, String image) {
	    	Product product = productDAO.getProduct(sku);
	        if (product != null) {
	            // Update the product properties
	                if (!name.isEmpty()) {
	                    product.setName(name);
	                }

	                if (!description.isEmpty()) {
	                    product.setDescription(description);
	                }

	                if (price >= 0) {
	                    product.setPrice(price);
	                }

	                if (!vendor.isEmpty()) {
	                    product.setVendor(vendor);
	                }

	                if (!image.isEmpty()) {
	                    product.setImage(image);
	                }
	        productDAO.updateProduct(product);	 
	        	}
	    }

	    public void deleteProduct(String urlSlug) {
	    	productDAO.deleteProductBySku(urlSlug);
	    }
	    
}
