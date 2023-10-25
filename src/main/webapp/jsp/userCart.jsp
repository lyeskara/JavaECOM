<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SOEN387.models.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<script src="https://unpkg.com/htmx.org@1.9.6" integrity="sha384-FhXw7b6AlE/jyjlZH5iHa/tTe9EpJ1Y55RjcgPbjeWMskSxZt1v9qkxLJWNJaGni" crossorigin="anonymous"></script>

</head>
<body>
  <h1><%= session.getAttribute("name") %></h1> 
   <% @SuppressWarnings("unchecked") // Suppress the warning
           List<Product> products = (List<Product>) request.getAttribute("products");  
           String sessionRoleParams = (String)session.getAttribute("role");
          %>
       <%if(products != null){ %>   
        <% for (Product product : products) { %>
            <li> <%= product.getName()%> - <%= product.getDescription() %> - <%= product.getPrice() %></li>
            <% if(!("staff".equals(sessionRoleParams)) || "".equals(sessionRoleParams)) {%>
               <button hx-delete="http://localhost:8080/JavaECOM/cart/products/<%=product.getUrlSlug()%>">delete from the cart</button>
            <% } %>
               
        <%}%>
       <%} %>
        
      
</body>
</html>