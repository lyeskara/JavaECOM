package com.SOEN387.controllers;

import java.io.IOException;
import java.util.List;

import com.SOEN387.models.Product;
import com.SOEN387.repositories.ProductRepository;
import com.SOEN387.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/products/download")
public class DownloadServlet extends HttpServlet {
	
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		  
		    List<Product> allProducts = ProductService.getAllProducts();
		 
	    }
	 

}