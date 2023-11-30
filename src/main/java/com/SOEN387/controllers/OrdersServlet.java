package com.SOEN387.controllers;

import com.SOEN387.models.Order;
import com.SOEN387.models.OrderItem;
import com.SOEN387.models.Product;
import com.SOEN387.models.User;
import com.SOEN387.services.CartService;
import com.SOEN387.services.OrderService;
import com.SOEN387.services.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
    private CartService cartService;
    private OrderService orderService;
    private UserService userService;

    public OrdersServlet() {
        cartService = new CartService();
        orderService = new OrderService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("name") == null) {
            // User is not logged in, redirect to login page
            response.sendRedirect("login");
            return;
        }

        String username = (String) session.getAttribute("name");
        List<Product> cartProducts = cartService.getCart(username);
        if (cartProducts == null || cartProducts.isEmpty()) {
            // Cart is empty, redirect to cart page or display a message
            response.sendRedirect("cart");
            return;
        }

        // Assuming we have a method to calculate the total price
        double totalPrice = calculateTotalPrice(cartProducts);

        // Create order items
        List<OrderItem> orderItems = new ArrayList<>();
        for (Product product : cartProducts) {
        	orderItems.add(new OrderItem(0, 0, product.getSku(), 1, product.getPrice()));
        }

        // Create the order
        Order order = new Order(0, getUserId(username), new Timestamp(System.currentTimeMillis()), totalPrice, "Pending");
        order.setOrderItems(orderItems);

        // Save the order using the OrderService
        orderService.createOrder(order);

        // Clear the cart after the order is created
        //cartService.clearCart(username);

        // Forward to the order confirmation page
        request.setAttribute("order", order); // Pass the order as an attribute
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/orderConfirmation.jsp");
        dispatcher.forward(request, response);
    }

    private double calculateTotalPrice(List<Product> products) {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
    
    private int getUserId(String passcode) {
        User user = userService.findByPasscode(passcode);
        return user != null ? user.getId() : -1;
    }
}