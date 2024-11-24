<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./Register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div class="background">
        <h1>Student Management System</h1>
        <div class="form-box">
            <h1>Register</h1>
            <h4>${msg }</h4>
            <form action="register" method="post">
                <div class="stu-detail">
                    <i class="fa-solid fa-user"></i>
                    <input type="text" name="sname" id="sname" placeholder="Username">
                </div>
                <div class="stu-detail">
                    <i class="fa-solid fa-envelope"></i>
                    <input type="email" name="semail" id="semail" placeholder="Email ">
                </div>
                <div class="stu-detail">
                    <i class="fa-solid fa-phone"></i>
                    <input type="text" name="sphone" id="sphone" placeholder="Phone Number">
                </div>
                <div class="stu-detail">
                    <label for="std" class="std">Std:</label>
                    <input type="text" name="std" id="std" placeholder="Standard">
                </div>
                <div class="stu-detail">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password" name="spassword" id="spassword" placeholder="Password">
                </div>
                <input type="submit" value="Register">
                <div class="login">
                    <p>Already have an account ?</p>
                    <a href="Login.jsp">Login</a>
                </div>

            </form>
        </div>
    </div>
</body>
</html>