<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SOEN387.models.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
</head>
<body>
  <h1><%= session.getAttribute("name") %></h1> 
   <% @SuppressWarnings("unchecked") // Suppress the warning
           List<Product> products = (List<Product>) request.getAttribute("products");  %>
           
        <% for (Product product : products) { %>
               <% String slug = product.getUrlSlug();%>
               <li> <%= product.getName()%> - <%= product.getDescription() %> - <%= product.getPrice() %></li>
               
        <%}%>
</body>
</html>