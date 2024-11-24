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

@WebServlet("/delete") // Ensures that the servlet is mapped to /delete
public class Delete extends HttpServlet {
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentEmail") == null) {
            // Forward to Login.jsp if user is not logged in
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
            return;
        }

        String studentEmail = (String) session.getAttribute("studentEmail");
        
        // Get a connection from the connection pool
        Connection connection = ConnectionPool.getConnection();

        try {
            // SQL query to delete the record from the database
            String sql = "DELETE FROM register WHERE semail = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, studentEmail);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Deletion successful; remove user session attributes
                session.invalidate(); // Invalidate the session to log the user out
                
                // Forward to Register.jsp after successful deletion
                request.setAttribute("msg", "Your account has been successfully deleted.");
                RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
                rd.forward(request, response);
            } else {
                // Handle if no record was deleted (e.g., error page or message)
                request.setAttribute("error", "Unable to delete account.");
                RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
                rd.forward(request, response);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred.");
            RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
            rd.forward(request, response);
        } finally {
            // Return the connection to the pool
            ConnectionPool.submitConnection(connection);
        }
    }
}
