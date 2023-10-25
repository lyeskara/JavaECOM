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
    String sessionCustomerParams = (String)session.getAttribute("name");
    String sessionRoleParams = (String)session.getAttribute("role");

 %>
<%if(product != null){ %>
     <p><%= product.getName() %> - <%= product.getDescription() %> - <%= product.getPrice() %></p>
<%} %>

<% if(!("staff".equals(sessionRoleParams))) {%>
    <button hx-post="http://localhost:8080/JavaECOM/cart/products/<%=product.getUrlSlug()%>">Add To Your Cart</button>
<% } %>

<% if(("staff".equals(sessionRoleParams))) {%>
      <form id="updateProductForm" action="http://localhost:8080/JavaECOM/products/<%=product.getUrlSlug()%>" method="post">
        <input type="text" name="name" placeholder="Product Name">
        <br>
        <input type="text" name="description" placeholder="Product Description">
        <br>
        <input type="text" name="vendor" placeholder="Vendor">
        <br>
        <input type="number" name="price" placeholder="Price">
        <br>
        <input type="text" name="SKU" placeholder="SKU">
        <br> 
        <button type="submit">Update Product</button>
    </form>
<% } %>



</body>
</html>