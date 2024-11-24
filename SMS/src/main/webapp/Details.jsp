<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
    /* HttpSession session = request.getSession(false); */
    if (session == null || session.getAttribute("studentName") == null) {
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
        return;
    }
    String studentName = (String) session.getAttribute("studentName");
    String studentEmail = (String) session.getAttribute("studentEmail");
    long studentPhone = (Long) session.getAttribute("studentPhone");
    int studentStandard = (Integer) session.getAttribute("standard");
    
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Details</title>
    <link rel="stylesheet" href="./Details.css">
</head>
<body>
    <div class="details-container">
        <h2>Student Details</h2>
        <p><span class="label">Name:</span> <%= studentName %></p>
        <p><span class="label">Email:</span> <%= studentEmail %></p>
        <p><span class="label">Phone:</span> <%= studentPhone %></p>
        <p><span class="label">Standard:</span> <%= studentStandard %></p>
        <p><span class="label">Password:</span> ********</p> <!-- Mask password -->
        <a href="Dashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
    