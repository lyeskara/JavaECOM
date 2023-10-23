<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.List" %>
<%@ page import="com.SOEN387.models.Product" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
<script src="https://unpkg.com/htmx.org@1.9.6" integrity="sha384-FhXw7b6AlE/jyjlZH5iHa/tTe9EpJ1Y55RjcgPbjeWMskSxZt1v9qkxLJWNJaGni" crossorigin="anonymous"></script>

</head>
<body>
 <% 
    Product product = (Product) request.getAttribute("product"); 
    String sessionParams = (String)session.getAttribute("name");
 %>
<p><%= product.getName() %> - <%= product.getDescription() %> - <%= product.getPrice() %></p>

<% if(!("staff".equals(sessionParams))) {%>
    <button hx-post="http://localhost:8080/JavaECOM/cart/products/<%=product.getUrlSlug()%>"
            hx-swap="OuterHTML"> Submit</button>
<% } %>
</body>
</html>