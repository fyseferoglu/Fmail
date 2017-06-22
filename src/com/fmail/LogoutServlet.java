package com.fmail;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException{
		
		HttpSession session=req.getSession(false);
		session.invalidate();
		req.setAttribute("logout","You have successfully logged out");
		RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
		rd.include(req,res);
		
		
	}
	
}
