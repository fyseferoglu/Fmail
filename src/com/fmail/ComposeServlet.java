package com.fmail;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.Date;

public class ComposeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException{
		
		
		HttpSession session=req.getSession(false);		
		Date date = new Date();
		java.sql.Date sqldate =new java.sql.Date(date.getTime());
		java.sql.Time sqltime = new java.sql.Time(date.getTime());
		ServletContext sc = getServletContext();
		String sender = (String) session.getAttribute("user");
		String to = req.getParameter("to");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		
		try {
			
			Class.forName(sc.getInitParameter("driverClassName"));
			Connection con = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"),sc.getInitParameter("password"));
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Mail_User WHERE email=?");
			ps.setString(1, to);
			ResultSet rs = ps.executeQuery();
			boolean st = rs.next();
			
			if(st){
				ps = con.prepareStatement("INSERT INTO INBOX"
						+ "(SENDER,RECEIVER,SUBJECT,MESSAGE,SENDINGDATE,SENDINGTIME) VALUES(?,?,?,?,?,?)");
				ps.setString(1, sender);
				ps.setString(2, to);
				ps.setString(3, subject);
				ps.setString(4, message);
				ps.setString(5,sqldate.toString());
				ps.setString(6, sqltime.toString());		
				int status=ps.executeUpdate();
				if(status > 0){
					req.setAttribute("compose", "Mail has been sent succesfully");
					RequestDispatcher rd = req.getRequestDispatcher("compose.jsp");
					rd.include(req, res);
					
				}
				else{
					 req.setAttribute("composeerror", "Mail could not be sent!");
					 RequestDispatcher rd = req.getRequestDispatcher("compose.jsp");
					 rd.include(req, res);
				}
			}
			else{
				req.setAttribute("composeerror", "Invalid Email address!");
				RequestDispatcher rd = req.getRequestDispatcher("compose.jsp");
				rd.include(req, res);			
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException,ServletException{
		
		res.sendRedirect("compose.jsp");
		
		
	}
	
}
