package com.SOEN387.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class AuthenticationServlet extends HttpServlet {
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Forward the request to the login.jsp page
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
	        dispatcher.forward(request, response);
	}
	 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	String name = request.getParameter("username");
    	String passcode = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800); // Set the session timeout to 30 minutes (1800 seconds)


    	if(passcode == null || (passcode.length() > 0 && !("secret".equals(passcode)) )) {
            response.sendRedirect("/login?error=true");
        }
        
        if ("secret".equals(passcode)) {
        	
            session.setAttribute("role", "staff");
            response.sendRedirect("/JavaECOM/products");
            
        } 
        
        if (passcode.isEmpty()) {
        	
            session.setAttribute("name", name);
            response.sendRedirect("/JavaECOM/products"); 
        } 
        
        
    }
}
