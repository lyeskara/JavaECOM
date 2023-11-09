<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SOEN387.models.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<!-- Include other scripts and styles if necessary -->
</head>
<body>
  <h1><%= session.getAttribute("name") %>'s Cart</h1>
  <% 
      List<Product> products = (List<Product>) request.getAttribute("products");  
      String sessionRoleParams = (String)session.getAttribute("role");
  %>
  <% if (products != null && !products.isEmpty()) { %>   
      <form action="orders" method="post">
          <ul>
              <% for (Product product : products) { %>
                  <li>
                      <%= product.getName()%> - <%= product.getDescription() %> - $<%= product.getPrice() %>
                      <% if (sessionRoleParams == null || !("staff".equals(sessionRoleParams))) { %>
                          <!-- Assuming hx-delete is part of your setup to handle AJAX requests -->
                          <button hx-delete="http://localhost:8080/JavaECOM/cart/products/<%=product.getUrlSlug()%>">Remove</button>
                      <% } %>
                  </li>
              <% } %>
          </ul>
          <!-- Checkout button -->
          <input type="submit" value="Order" />
      </form>
  <% } else { %>
      <p>Your cart is empty.</p>
  <% } %>
</body>
</html>
