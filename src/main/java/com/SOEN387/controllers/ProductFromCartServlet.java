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
		  
		HttpSession session = request.getSession(); // Get the session without creating a new one
		 if(session == null) {
		        // No session found; handle the situation as needed'

		 }
		 String name = (String) session.getAttribute("name");
		 if (name == null) {  
	            // No user name found; handle the situation as needed

		 }  
		 
		 String PathInfo = request.getPathInfo();
		 String urlSlug = PathInfo.substring(1);
		 String SKU = (ProductRepository.getProductByUrlSlug(urlSlug)).getSku();
		 CartRepository.AddProductToCart(name, SKU);
		 
		 // jsp work here
		 
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userCart.jsp");
		 dispatcher.forward(request, response);
		 
		 
 }
	
	 
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
		 
		 String urlSlug = request.getPathInfo();
		 String SKU = (ProductRepository.getProductByUrlSlug(urlSlug)).getSku();
		 CartRepository.RemoveProductFomCart(urlSlug, SKU);
		 
		 // jsp
		 		 
	 }	        
		     
	   
	 
}


