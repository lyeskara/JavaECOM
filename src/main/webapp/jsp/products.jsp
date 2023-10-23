<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SOEN387.models.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Some</title>
</head>
<body>
   <h1>Products</h1>
    <ul>
        
        <% @SuppressWarnings("unchecked") // Suppress the warning
           List<Product> products = (List<Product>) request.getAttribute("products");  %>
           
        <% for (Product product : products) { %>
               <% String slug = product.getUrlSlug();%>
               <li><a href="/JavaECOM/products/<%=slug%>"> <%= product.getName()%> - <%= product.getDescription() %> - <%= product.getPrice() %></a></li>
               
        <%}%>
    </ul>
</body>
</html>