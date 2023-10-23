package com.SOEN387.controllers;

import java.io.IOException;

import java.util.List;

import com.SOEN387.models.Product;
import com.SOEN387.services.ProductService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
	
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		    List<Product> products = ProductService.getAllProducts();
		    request.setAttribute("products", products);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/products.jsp");
		    dispatcher.forward(request, response);
	       
	    }

}
