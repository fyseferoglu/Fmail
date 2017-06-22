package com.fmail;
import java.io.IOException;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import com.fmail.md5.*;
public class LoginController extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
	
		String mail = req.getParameter("inputEmail");
		String pass = req.getParameter("inputPassword");
		ServletContext sc = getServletContext();	
		try {
			Class.forName(sc.getInitParameter("driverClassName"));
			Connection con = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"),sc.getInitParameter("password"));
			PreparedStatement ps = con.prepareStatement("Select * from Mail_User where email=? and password=?");
			ps.setString(1, mail);
			ps.setString(2, MD5Encryption.getPassword(pass));
			ResultSet rs = ps.executeQuery(); 
			boolean st = rs.next();
			if(st){
				HttpSession session = req.getSession();
				session.setAttribute("user", mail);				
				RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
				rd.forward(req, res);
				//res.sendRedirect("home.jsp");
			}				
			else{
				req.setAttribute("Error", "User name or password wrong!");				
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");			
				rd.include(req, res);
				//res.sendRedirect("index.jsp");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		
		res.sendRedirect("home.jsp");
		
		
	}

}
