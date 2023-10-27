package com.SOEN387.repositories;

import java.util.ArrayList;
import java.util.List;
import com.SOEN387.models.Product;

public class ProductRepository {
	
	private static List<Product> products = new ArrayList<>();
	
	public ProductRepository() {}
	
	static {
        // Create and add 5 sample products
        products.add(new Product("M5", "World class car", "BMW", "1", "A", 250000.0, "https://images.pexels.com/photos/11291694/pexels-photo-11291694.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        products.add(new Product("AMG-GT", "World class car", "Mercedes", "2", "B", 350000.0, "https://images.pexels.com/photos/18725051/pexels-photo-18725051/free-photo-of-sports-car-with-spoiler.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        products.add(new Product("7 series", "World class car", "BMW", "3", "C", 150000.0, "https://images.pexels.com/photos/2526128/pexels-photo-2526128.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        products.add(new Product("S-class", "World class car", "BMW", "4", "D", 100000.0, "https://images.pexels.com/photos/14065189/pexels-photo-14065189.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        products.add(new Product("Maybach", "World class car", "Mercedes", "5", "E", 450000.0, "https://images.pexels.com/photos/9275801/pexels-photo-9275801.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
	}
	
	public static List<Product> getAllProducts(){
		return products;
	}
	
	public static void CreateProduct(String sku,String name) {
        // Assign a unique ID and add the product to the list
		String urlSlug = Integer.toString((products.size()+1));
        products.add(new Product(name, "No description added", "No vendor mentioned", urlSlug , sku, 0.0, "No image"));
    }
	
	public static void AddProduct(Product product) {
        // Assign a unique ID and add the product to the list
        products.add(product);
    }
	
//	public static void addProduct(Product product) {
//		products.add(product);
//	}
	
	public static void deleteProduct(String urlSlug) {
        products.removeIf(product -> product.getUrlSlug() == urlSlug);
	}
	
	public static void UpdateProduct(String sku,  String name, String description, double price, String vendor, String image) {
		 for (Product product : products) {
		        if (product.getSku().equals(sku)) {
		            // Update the fields with new values (if not null or empty)
		            if (name != null && !name.isEmpty()) {
		                product.setName(name);
		            }
		            if (description != null && !description.isEmpty()) {
		                product.setDescription(description);
		            }
		            if (price >= 0) {
		                product.setPrice(price);
		            }
		            if (vendor != null && !vendor.isEmpty()) {
		                product.setVendor(vendor);
		            }
		            
		            if (image != null && !image.isEmpty()) {
		                product.setVendor(image);
		            }
		            
		            break;
		        }
		    }	
	}
//	public static void updateProduct(Product updatedProduct) {
//	    // Find and update the product with the same ID
//	    for (int i = 0; i < products.size(); i++) {
//	        if (products.get(i).getUrlSlug() == updatedProduct.getUrlSlug()) {
//	            Product updated = new Product(updatedProduct);
//	            products.set(i, updated);
//	            break;
//	        }
//	    }
//	}
	
	public static Product getProduct(String sku) {
	    for (Product product : products) {
	        if (product.getSku().equals(sku)) {
	            return product; // Return the product with the matching URL slug
	        }
	    }
	    return null; // Return null if no product with the URL slug is found
	}
	
	public static Product getProductByUrlSlug(String urlSlug) {
	    for (Product product : products) {
	        if (product.getUrlSlug().equals(urlSlug)) {
	            return product; // Return the product with the matching URL slug
	        }
	    }
	    return null; // Return null if no product with the URL slug is found
	}
	
	

}
