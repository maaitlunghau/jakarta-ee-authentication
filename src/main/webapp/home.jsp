<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | Jakarta EE Auth</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="welcome-card">
            <% 
                String username = "Guest";
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("username")) {
                            username = cookie.getValue();
                            break;
                        }
                    }
                }
            %>
            <div class="user-avatar">
                <%= username.substring(0, 1).toUpperCase() %>
            </div>
            <h1>Hello, <%= username %>!</h1>
            <p class="subtitle">You have successfully logged into the system.</p>

            <form action="controller" method="POST">
                <button type="submit" name="action" value="Logout" class="btn btn-secondary">Sign Out</button>
            </form>
        </div>
    </div>
</body>
</html>
