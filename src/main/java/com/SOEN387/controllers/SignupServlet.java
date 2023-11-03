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
    private ObjectMapper objectMapper;
    private HashMap<String, String> passcodes;

    public SignupServlet() {
        userService = new UserService();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        passcodes = new HashMap<>();

        // Load passcodes from the JSON file
        loadPasscodesFromFile();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the login.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/signup.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String passcode = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800); // Set the session timeout to 30 minutes (1800 seconds)

        if (passcode == null) {
            response.sendRedirect("/login?error=true");
            return;
        }

        if ("secret".equals(passcode)) {
            session.setAttribute("role", "staff");
            response.sendRedirect("/JavaECOM/products");
        } else {
            boolean exists = userService.createUser(name);
            if (exists) {
                response.setStatus(HttpServletResponse.SC_CONFLICT); // HTTP 409 Conflict
                response.getWriter().write("User with the same username already exists.");
            } else {
                // Add the new passcode to the HashMap
                passcodes.put(name, passcode);

                // Save the updated passcodes to the JSON file
                savePasscodesToFile();

                session.setAttribute("name", name);
                response.sendRedirect("/JavaECOM/products");
            }
        }
    }

    private void loadPasscodesFromFile() {
        try {
            File file = new File("C:\\Users\\lyesk\\eclipse-workspace\\JavaECOM\\src\\main\\java\\com\\SOEN387\\user_management.json"); // Specify the path to your JSON file
            if (file.exists()) {
                passcodes = objectMapper.readValue(file, HashMap.class);
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePasscodesToFile() {
        try {
            File file = new File("C:\\Users\\lyesk\\eclipse-workspace\\JavaECOM\\src\\main\\java\\com\\SOEN387\\user_management.json"); // Specify the path to your JSON file
            objectMapper.writeValue(file, passcodes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ...
}

