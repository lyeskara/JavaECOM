<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SOEN387.models.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="jsp/css/styles.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Some</title>
<script src="https://unpkg.com/htmx.org@1.9.6" integrity="sha384-FhXw7b6AlE/jyjlZH5iHa/tTe9EpJ1Y55RjcgPbjeWMskSxZt1v9qkxLJWNJaGni" crossorigin="anonymous"></script>

</head>
<body>
<div class="video-background">
    <video autoplay="autoplay" muted="muted" loop="loop" >
        <source src="jsp/assets/videos/neon.mp4" type="video/mp4">
    </video>
</div>

        <% @SuppressWarnings("unchecked") // Suppress the warning
           List<Product> products = (List<Product>) request.getAttribute("products");
           String sessionRoleParams = (String)session.getAttribute("role");
        %>

<nav class="Navbar">
    <a href="/JavaECOM" class="link">Home</a>
    <a href="products" class="link">Products</a>
    
    <% if("staff".equals(sessionRoleParams)) { %>
        <a href="http://localhost:8080/JavaECOM/logout" class="link">Logout</a>
    <% } else { %>
        <a href="login" class="link">Login</a>
    <% } %>
</nav>

   <h1>Products</h1>
    <ul>
        

        
          
    <div class="product-grid">
         
    <% for (Product product : products) { %>
        <% String slug = product.getUrlSlug(); %>
        
        <div class="card">
            <!-- Assuming the Product model has a method called getImageUrl() to retrieve image URL for the product -->
            <div class="imgBox">
                <img src="<%= product.getImage() %>" alt="<%= product.getName() %>" class="mouse">
            </div>
            <div class="contentBox">
                <h3><%= product.getName() %></h3>
                <h2 class="price"><%= product.getPrice() %> $</h2>
                <a href="/JavaECOM/products/<%=slug%>" class="buy">Buy Now</a>
            </div>
        </div>
    <% } %>
    
                </div>
    
        
<% if(("staff".equals(sessionRoleParams))) {%>
    <a href="http://localhost:8080/JavaECOM/products/download" class="join-btn" download>Download Products</a>
<% } %>
        
    </ul>
</body>
</html>


