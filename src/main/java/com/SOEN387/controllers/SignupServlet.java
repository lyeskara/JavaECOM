package com.SOEN387.controllers;

import java.io.IOException;


import com.SOEN387.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



import java.io.File;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

// ...

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private UserService userService;

    public SignupServlet() {
        userService = new UserService();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the login.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/signup.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String admin = request.getParameter("admin");
        String passcode = request.getParameter("passcode");
        
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800); // Set the session timeout to 30 minutes (1800 seconds)

        if (passcode == null || passcode.equals("")) {
            response.sendRedirect("/login?error=true");
            return;
        }

        if ("secret".equals(admin)) {
        	boolean exists = userService.createUser(passcode, true);
            if (exists) {
                response.setStatus(HttpServletResponse.SC_CONFLICT); // HTTP 409 Conflict
                response.getWriter().write("User with the same username already exists.");
            } else {
            session.setAttribute("name", passcode);
            session.setAttribute("role", "staff");
            response.sendRedirect("/JavaECOM/products");
            }
        } else {
            boolean exists = userService.createUser(passcode, false);
            if (exists) {
                response.setStatus(HttpServletResponse.SC_CONFLICT); // HTTP 409 Conflict
                response.getWriter().write("User with the same username already exists.");
            } else {
            
                session.setAttribute("name", passcode);
                response.sendRedirect("/JavaECOM/products");
            }
        }
    }

   

  

    // ...
}

