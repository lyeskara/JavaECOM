package com.SOEN387.controllers;

import java.io.IOException;

import com.SOEN387.repositories.CartRepository;
import com.SOEN387.repositories.ProductRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/cart/products/*")
public class ProductFromCartServlet extends HttpServlet {
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		  
		HttpSession session = request.getSession(false); // Get the session without creating a new one (use 'false')
        String name = "";
		try {
		    if (session == null) {
		        throw new IllegalStateException("No session found");
		    }
		    name = (String) session.getAttribute("name");
		    if (name == null) {
		        throw new IllegalArgumentException("No user name found");
		    }
		    // If both the session and name are available, continue with your business logic.
		    // For example, display a welcome message or perform user-specific actions.
		    response.getWriter().write("Welcome, " + name);
		} catch (IllegalStateException e) {
		    // Handle the case where no session is found
		    response.getWriter().write("Error: " + e.getMessage());
		} catch (IllegalArgumentException e) {
		    // Handle the case where no user name is found
		    response.getWriter().write("Error: " + e.getMessage());
		}
 
		 
		 String PathInfo = request.getPathInfo();
		 String urlSlug = PathInfo.substring(1);
		 String SKU = (ProductRepository.getProductByUrlSlug(urlSlug)).getSku();
		 CartRepository.AddProductToCart(name, SKU);
		 
		 // jsp work here
		 
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userCart.jsp");
		 dispatcher.forward(request, response);
		 
		 
 }
	
    @Override
	 protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 HttpSession session = request.getSession(); // Get the session without creating a new one
		 if(session == null) {
		        // No session found; handle the situation as needed'
			 
			 
		 }
		 String name = (String) session.getAttribute("name");
		 if (name == null) {  
	            // No user name found; handle the situation as needed

		 } 
		 
		 String pathInfo = request.getPathInfo();
		 String urlSlug = pathInfo.substring(1);
		 String SKU = (ProductRepository.getProductByUrlSlug(urlSlug)).getSku();

		 CartRepository.RemoveProductFomCart(name, SKU);
		 
		 // jsp
		 		 
	 }	        
		     
	   
	 
}


