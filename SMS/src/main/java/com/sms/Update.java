package com.sms;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update")
public class Update extends HttpServlet {
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentEmail") == null) {
            // Redirect to Login.jsp if user is not logged in
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
            return;
        }

        // Retrieve updated data from the form
        String name = request.getParameter("sname");
        String email = request.getParameter("semail");
        String phoneStr =request.getParameter("sphone");
        String standard =request.getParameter("std");
        String password = request.getParameter("spassword");

        // Validate and parse phone number
        long phone = Long.parseLong(phoneStr);
        int std = Integer.parseInt(standard);

        // Get a connection from the connection pool
        Connection connection = ConnectionPool.getConnection();

        try {
            // SQL query to update the student record
            String sql = "UPDATE register SET sname = ?, phone = ?, std = ? WHERE semail = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setLong(2, phone);
            stmt.setInt(3, std);
            stmt.setString(4, email);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Update successful; update session attributes
                session.setAttribute("studentName", name);
                session.setAttribute("studentPhone", phone);
                session.setAttribute("studentStandard", std);
                

                // Set success message and forward back to Dashboard.jsp
                request.setAttribute("msg", "Details updated successfully.");
                RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
                rd.forward(request, response);
            } else {
                // Handle if no record was updated
                request.setAttribute("error", "Unable to update details.");
                RequestDispatcher rd = request.getRequestDispatcher("Update.jsp");
                rd.forward(request, response);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred.");
            RequestDispatcher rd = request.getRequestDispatcher("Update.jsp");
            rd.forward(request, response);
        } finally {
            // Return the connection to the pool
            ConnectionPool.submitConnection(connection);
        }
    }
}

