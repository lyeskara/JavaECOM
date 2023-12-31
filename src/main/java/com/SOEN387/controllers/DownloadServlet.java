package com.SOEN387.controllers;

import java.io.IOException;




import java.util.List;

import com.SOEN387.models.Product;
import com.SOEN387.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;



@WebServlet("/products/download")
public class DownloadServlet extends HttpServlet {
	
	 private ProductService productService;
	 
	 public DownloadServlet() {
		 productService = new ProductService();
	 }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, JsonProcessingException  {
		  
		    List<Product> allProducts = productService.getAllProducts();
		   
		    // Convert the products list to JSON
	        ObjectMapper objectMapper = new ObjectMapper();
	        String productsJson = objectMapper.writeValueAsString(allProducts);

	        response.setCharacterEncoding("UTF-8"); // Set encoding to UTF-8
	        response.setContentType("application/json");
	        response.setHeader("Content-Disposition", "attachment; filename=products.json");

	        // Get the output stream to write the JSON data
	        try (ServletOutputStream out = response.getOutputStream()) {
	            out.print(productsJson);
	        }
	    }
	 

}