package com.fmail;
import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		
		String query = req.getParameter("q");
		ServletContext sc = getServletContext();
		try{
			Class.forName(sc.getInitParameter("driverClassName"));
			Connection con = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("username"),sc.getInitParameter("password"));
			PreparedStatement ps = con.prepareStatement("DELETE FROM INBOX WHERE MAILID=?");
			ps.setString(1, query);
			int status = ps.executeUpdate();
			if(status > 0){
				req.setAttribute("delete", "The mail has been deleted succesfully");
				RequestDispatcher rd = req.getRequestDispatcher("inbox.jsp");
				rd.forward(req, res);
			}
			else{
				req.setAttribute("deleteError", "The mail cannot be deleted!");
				RequestDispatcher rd = req.getRequestDispatcher("inbox.jsp");
				rd.forward(req, res);				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
