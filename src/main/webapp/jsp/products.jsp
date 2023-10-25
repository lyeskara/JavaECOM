<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SOEN387.models.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Some</title>
<script src="https://unpkg.com/htmx.org@1.9.6" integrity="sha384-FhXw7b6AlE/jyjlZH5iHa/tTe9EpJ1Y55RjcgPbjeWMskSxZt1v9qkxLJWNJaGni" crossorigin="anonymous"></script>

</head>
<body>
   <h1>Products</h1>
    <ul>
        
        <% @SuppressWarnings("unchecked") // Suppress the warning
           List<Product> products = (List<Product>) request.getAttribute("products");
           String sessionRoleParams = (String)session.getAttribute("role");
        %>
        
           
        <% for (Product product : products) { %>
               <% String slug = product.getUrlSlug();%>
               <li><a href="/JavaECOM/products/<%=slug%>"> <%= product.getName()%> - <%= product.getDescription() %> - <%= product.getPrice() %></a></li>
               
        <%}%>
        
        <% if(("staff".equals(sessionRoleParams))) {%>
               <button hx-get="http://localhost:8080/JavaECOM/products/download" >Download Products</button>
               <button hx-get="http://localhost:8080/JavaECOM/logout" >logout</button>
               
        <% } %>
        
    </ul>
</body>
</html>