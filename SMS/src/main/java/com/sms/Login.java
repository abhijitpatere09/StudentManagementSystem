package com.sms;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String studentEmail = req.getParameter("semail");
		String password = req.getParameter("spassword");

		Connection connection = ConnectionPool.getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM register WHERE semail=?");
			ps.setString(1, studentEmail);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				String dbPassword = rs.getString(5);
				
				if (dbPassword.equals(password)) {
					
					String studentName = rs.getString(1);
					int standard = rs.getInt(4);
					long phone = rs.getLong(3);
					
					HttpSession session = req.getSession();
					
					session.setAttribute("studentEmail",studentEmail);  //set email in session
					session.setAttribute("studentName",studentName);   //set name in session
					session.setAttribute("studentPhone",phone);        //set phone in session
					 session.setAttribute("studentPassword",dbPassword);   //set password in session
					 session.setAttribute("standard",standard);         //set std in session
					
					RequestDispatcher rd = req.getRequestDispatcher("Dashboard.jsp");
					rd.forward(req, resp);
				}else {
					req.setAttribute("msg", "Wrong Password");
					RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
					rd.forward(req, resp);
				}
			} else {
				req.setAttribute("msg", "User Not Registered");
				RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
				rd.forward(req, resp);
			}
			
		} catch (SQLException e) {
			req.setAttribute("msg", "Something went wrong please try again later");
			RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
			rd.forward(req, resp);
			e.printStackTrace();
		} finally {
			ConnectionPool.submitConnection(connection);
		}

	}
}
