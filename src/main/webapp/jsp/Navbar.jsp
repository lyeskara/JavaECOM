
<nav class="Navbar">
    <a href="/JavaECOM" class="link">Home</a>
    <a href="products" class="link">Products</a>
    
    <% String userName = (String)session.getAttribute("name"); %>
    <% if(userName != null && !userName.isEmpty()) { %>
        <a href="logout" class="link">Logout</a>
    <% } else { %>
        <a href="login" class="link">Login</a>
    <% } %>
</nav>