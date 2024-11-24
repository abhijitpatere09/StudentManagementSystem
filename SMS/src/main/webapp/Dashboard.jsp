<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    /* HttpSession session = request.getSession(false); */ // Get the current session, if exists
    if (session == null || session.getAttribute("studentName") == null) {
        // Forward to Login.jsp if no active session or studentName attribute is missing
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
        return;
    }
    String studentName = (String) session.getAttribute("studentName"); // Retrieve student name from session
    String studentEmail = (String) session.getAttribute("studentEmail"); // Retrieve student email from session
%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="Dashboard.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
      integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body>
    
    <div class="sidebar">
      <div class="logo">
        <img
          src="./images/logo.png"
          alt="logo"
          height="100px"
          width="100px"
        />
        <h2>SMS</h2>
      </div>
      <ul class="menu">
        <li class="active">
          <a href="Dashboard.jsp"
            ><i class="fa fa-dashboard"></i> <span>Dashboard</span></a
          >
        </li>
        <li>
          <a href="Details.jsp"
            ><i class="fa-solid fa-circle-user"></i> <span>Details</span></a
          >
        </li>
        <li>
          <a href="Update.jsp"
            ><i class="fas fa-edit"></i> <span>Update</span></a
          >
        </li>
        <li class="delete">
          <form
            action="delete"
            method="post"
            onsubmit="return confirm('Are you sure you want to delete your account?');"
          >
            
            <button type="submit"><i class="fa-solid fa-trash"></i><span>Delete</span></button>
          </form>
        </li>
        <li class="logout">
          <a href="<%= request.getContextPath() %>/logout"
            ><i class="fa-solid fa-right-from-bracket"></i>
            <span>Logout</span></a
          >
        </li>
      </ul>
    </div>
    <div class="main-content">
      <div class="header">
        <div class="left-content">
          <h2>Welcome</h2>
       	<h1><%= studentName %>!</h1>
        </div>
        <div class="right-content">
          <a href="./Register.jsp"><button type="button">Register</button></a>
          <a href="./Login.jsp"><button type="button">Login</button></a>
          <a href="./Details.jsp"><img src="./images/student.png" alt="" height="80px"
            width="80px"></a>
        </div>
      </div>
      <div class="main">
      <h4>${msg }</h4>
        <div class="container">
          <div class="info">
            <h1>Student management System</h1>
            <p>It is a solution tool that is designed to track, maintain and manage all the data of Students.</p>
          </div>
          <div class="main-img">
            <img src="./images/main.JPG" alt="home-img">
          </div>
        </div>
      </div>

    </div>
  </body>
</html>
