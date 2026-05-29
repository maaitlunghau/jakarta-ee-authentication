<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | Jakarta EE Auth</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Welcome Back</h1>
        <p class="subtitle">Please enter your details to sign in</p>

        <% String err = (String) request.getAttribute("errMes"); %>
        <% if (err != null) { %>
            <div class="alert alert-error">
                <%= err %>
            </div>
        <% } %>

        <% String ss = (String) request.getAttribute("ssMes"); %>
        <% if (ss != null) { %>
            <div class="alert alert-success">
                <%= ss %>
            </div>
        <% } %>

        <form action="controller" method="POST">
            <div class="form-group">
                <label for="txtName">Username</label>
                <input type="text" id="txtName" name="txtName" placeholder="Enter your username" required>
            </div>
            <div class="form-group">
                <label for="txtPass">Password</label>
                <input type="password" id="txtPass" name="txtPass" placeholder="••••••••" required>
            </div>
            
            <button type="submit" name="action" value="Login" class="btn">Sign In</button>
        </form>

        <p class="footer-text">
            Don't have an account? <a href="register.jsp">Sign up for free</a>
        </p>
    </div>
</body>
</html>
