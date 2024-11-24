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

@WebServlet("/register")
public class Register extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String studentName = req.getParameter("sname");
		String studentEmail=req.getParameter("semail");
		String studentPhone=req.getParameter("sphone");
		String studentStandard=req.getParameter("std");
		String password=req.getParameter("spassword");
		
		Connection connection = ConnectionPool.getConnection();
		
		// Convert studentPhone to long for BIGINT compatibility
        long studentPhoneNo = Long.parseLong(studentPhone);
        int  studentStd	=Integer.parseInt(studentStandard);
		
		try {
			PreparedStatement ps=connection.prepareStatement("INSERT INTO register VALUES(?,?,?,?,?)");
			ps.setString(1, studentName);
			ps.setString(2, studentEmail);
			ps.setLong(3, studentPhoneNo);
			ps.setInt(4, studentStd);
			ps.setString(5, password);
			
			int insert=ps.executeUpdate();
			
			if (insert>0) {
				req.setAttribute("msg", "registration successful");
				RequestDispatcher rd=req.getRequestDispatcher("Login.jsp");
				rd.forward(req, resp);
			} else {
				req.setAttribute("msg", "registration failed");
				RequestDispatcher rd=req.getRequestDispatcher("Register.jsp");
				rd.forward(req, resp);
			}
		} catch (SQLException e) {
			req.setAttribute("msg", "Email is already registered");
			RequestDispatcher rd=req.getRequestDispatcher("Register.jsp");
			rd.forward(req, resp);
			e.printStackTrace();
		}finally {
			ConnectionPool.submitConnection(connection);
		}
		
	}
}
