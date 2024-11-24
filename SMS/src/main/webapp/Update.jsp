<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% 
String studentName =(String)session.getAttribute("studentName"); 
String studentEmail =(String)session.getAttribute("studentEmail");
Long studentPhone =(Long)session.getAttribute("studentPhone"); 
String studentPassword =(String)session.getAttribute("studentPassword"); 
int studentStandard =(Integer)session.getAttribute("standard"); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Update Details</title>
    <link rel="stylesheet" href="./Update.css" />
  </head>
  <body>
    <div class="update-container">
      <h2>Update Details</h2>
      <h5>${error}</h5>
      <form action="update" method="post">
        <div class="update-details">
          <label for="sname">Name:</label>
          <input
            type="text"
            name="sname"
            id="sname"
            value="<%=studentName%>"
            required
          />
        </div>
        <div class="update-details">
          <label for="semail">Email:</label>
          <input
            type="email"
            name="semail"
            id="semail"
            value="<%=studentEmail%>"
            readonly
          />
        </div>
        <div class="update-details">
          <label for="sphone">Phone:</label>
          <input
            type="text"
            name="sphone"
            id="sphone"
            value="<%=studentPhone%>"
            required
          />
        </div>
        <div class="update-details">
          <label for="std">Standard:</label>
          <input
            type="text"
            name="std"
            id="std"
            value="<%=studentStandard%>"
            required
          />
        </div>
        <%--
        <div class="update-details">
          <label for="spassword">Password:</label>
          <input
            type="text"
            name="spassword"
            id="spassword"
            value="<%=studentPassword%>"
            required
          />
        </div>
        --%>

        <input type="submit" value="Update" />
      </form>
      <div class="dash">
        <a href="Dashboard.jsp">Back Dashboard</a>
      </div>
    </div>
  </body>
</html>
    