package com.SOEN387.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.SOEN387.models.Product;
import com.SOEN387.repositories.ProductRepository;

public class CartRepository {

	private static HashMap<String, List<Product>> cart = new HashMap<>();
	
	public CartRepository() {}
	
	
	public static List<Product> getCart(String user){
		return cart.getOrDefault(user, new ArrayList<>());
	}
	
	public static void AddProductToCart(String user, String sku) {
		List<Product> userCart = cart.computeIfAbsent(user, k -> new ArrayList<>());
		Product product = ProductRepository.getProduct(sku);
		if(product != null && !userCart.contains(product)) {
			userCart.add(product);
		}
		
		//
		
	}
	
	public static void RemoveProductFomCart(String user, String sku) {
		List<Product> userCart = cart.get(user);
        if (userCart != null) {
            userCart.removeIf(product -> product.getSku().equals(sku));
        }
	}
	
	
}
