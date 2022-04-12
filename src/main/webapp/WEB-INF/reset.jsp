<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password.</p>
        <form action="reset" method="POST">
            <label for="email">Email address: &nbsp;</label>
            <input type="email" name="email" id="email" > <!-- required> -->
            <button type="submit">Reset</button>
        </form>
        <p>${message}</p>
        <a href="login">Login</a>
    </body>
</html>
