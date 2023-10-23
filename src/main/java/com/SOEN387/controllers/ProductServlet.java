package com.SOEN387.controllers;

import java.io.IOException;

import com.SOEN387.models.Product;
import com.SOEN387.repositories.ProductRepository;
import com.SOEN387.services.ProductService;

import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		  String urlSlugPath = request.getPathInfo();
		  String urlSlug = urlSlugPath.substring(1);
		 
		  Product product = ProductService.getProductByUrlSlug(urlSlug);
		  
		  request.setAttribute("product", product);
		  
		  RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Product.jsp");
		  dispatcher.forward(request, response);
	       
	    }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 HttpSession session = request.getSession(); // Get the session without creating a new one
		 if(session == null) {
		        // No session found; handle the situation as needed'
			 
		 }
		 String role = (String) session.getAttribute("role");
		 if (role == null || !("staff".equals(role))) {  
                 
		 }
		 
		 String urlSlug = request.getPathInfo();
		 Product product = ProductService.getProductByUrlSlug(urlSlug);
		 
		 String SKU = product.getSku();
		 String name = request.getParameter("name");
		 String description = request.getParameter("description");
		 String vendor = request.getParameter("vendor");
		 double price = Double.parseDouble(request.getParameter("price"));
		  
		 ProductService.updateProduct(SKU, name, description, price, vendor);
		 
}
	 protected void doPut(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 HttpSession session = request.getSession(); // Get the session without creating a new one
		 if(session == null) {
		        // No session found; handle the situation as needed'
			 
			 
		 }
		 
		 String role = (String) session.getAttribute("role");
		 if (role == null || !("staff".equals(role))) {  

		 }  
	    }
	 protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		  
		 HttpSession session = request.getSession(false); // Get the session without creating a new one
		 if(session == null) {
		        // No session found; handle the situation as needed'
			 
			 
		 }
		 
		 String role = (String) session.getAttribute("role");
		 if (role == null || !("staff".equals(role))) {  

		 }  
		 
	       
	    }

}
