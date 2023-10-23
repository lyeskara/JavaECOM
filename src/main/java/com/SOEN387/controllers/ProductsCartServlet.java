package com.SOEN387.controllers;

import java.io.IOException;
import java.util.List;

import com.SOEN387.models.Product;
import com.SOEN387.repositories.CartRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart/products")
public class ProductsCartServlet extends HttpServlet {
		 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		
		 HttpSession session = request.getSession(false); 
		 if(session == null) {
		        // No session found; handle the situation as needed' 
		 }
		 String name = (String) session.getAttribute("name");
		 if (name == null) {  
	            // No user name found; handle the situation as needed

		 }  
		 
		 List<Product> userProducts = CartRepository.getCart(name);
		 request.setAttribute("products", userProducts);
		 request.setAttribute("name", name);

		 RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userCart.jsp");
		 dispatcher.forward(request, response);
	} 
	
}

