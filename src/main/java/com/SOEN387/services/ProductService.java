package com.SOEN387.services;


import java.util.List;
import com.SOEN387.models.Product;
import com.SOEN387.repositories.ProductRepository;

	
public class ProductService {
	
	    public static List<Product> getAllProducts() {
	        return ProductRepository.getAllProducts();
	    }

	    public static Product getProductByUrlSlug(String urlSlug) {
	        return ProductRepository.getProductByUrlSlug(urlSlug);
	    }

	    public static void addProduct(String sku, String name) {
	    	ProductRepository.CreateProduct(sku, name);
	    }

	    public static void updateProduct(String sku,  String name, String description, double price, String vendor) {
	    	ProductRepository.UpdateProduct(sku, name, description, price, vendor );	    
	    }

	    public static void deleteProduct(String urlSlug) {
	        ProductRepository.deleteProduct(urlSlug);
	    }
	    
}
