<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter a new Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your new password carefully.</p>
        <form action="reset" method="POST">
            <!--
            <label for="password">New Password: </label>
            <input type="hidden" name="password" id="password" required>
            <button type="submit">Submit</button>
            -->
            <input type="hidden" name="uuid" id="uuid" value="${uuid}">
            <label for="email">Enter your email: </label>
            <input type="email" name="email" id="email" required><br>
            <label for="password">New Password: </label>
            <input type="password" name="password" id="password" required>
            <button type="submit">Reset Password</button>    
        </form>
        <p>${message}</p>
        <a href="login">Login</a>
    </body>
</html>
