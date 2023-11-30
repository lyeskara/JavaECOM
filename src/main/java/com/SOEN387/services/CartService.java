package com.SOEN387.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.SOEN387.DAOs.CartDAO;
import com.SOEN387.DAOs.ProductDAO;
import com.SOEN387.DAOs.UserDAO;
import com.SOEN387.models.Product;

public class CartService {

	private CartDAO cartDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    
    public CartService() {
        cartDAO = new CartDAO();
        productDAO = new ProductDAO();
        userDAO = new UserDAO();
    }
	
	
    public List<Product> getCart(String passcode) {
       
        int userId = userDAO.getUserIDByPasscode(passcode);
        if(userId == -1) {
        	return null;
        }
        return cartDAO.getCartItems(userId);
    }
	
    public void SetProductQuantityInCart(String passcode, String sku, int Quantity) {
		/*
		 * Sets the quantity of a given product in the cart
           associated with the user. If the quantity is greater than zero and if no cart is associated with the
           user, create one first.
		 * */
    	
    	int userId = userDAO.getUserIDByPasscode(passcode);
        Product product = productDAO.getProduct(sku);
        int productId = productDAO.getProductIDByName(product.getName());
        if (product != null && userId >= 0 && productId >= 0) {
            cartDAO.UpdateQuantity(userId, productId, Quantity);
        }
    	
		
	}
	
	 public void addProductToCart(String passcode, String sku) {
	       
	        int userId = userDAO.getUserIDByPasscode(passcode);
	        Product product = productDAO.getProduct(sku);
            int productId = productDAO.getProductIDByName(product.getName());

	        if (product != null && userId >= 0 && productId >= 0) {
	            cartDAO.addToCart(userId, productId);
	        }
	    }
	
	 public void removeProductFromCart(String passcode, String sku) {
	        
	        int userId = userDAO.getUserIDByPasscode(passcode);
	        Product product = productDAO.getProduct(sku);
            int productId = productDAO.getProductIDByName(product.getName());
            
	        if (product != null && userId >= 0 && productId >= 0) {
	            cartDAO.removeFromCart(userId, productId);
	        }
	    }
	
	
}
