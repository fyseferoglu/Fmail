package com.fmail;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Calendar;
import com.fmail.md5.*;
public class SignupServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{		
		
		String firstname = req.getParameter("firstName");
		String lastname = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String conpassword = req.getParameter("conpassword");
		String gender = req.getParameter("gender");
		String location = req.getParameter("location");
		java.util.Date sqdate=Calendar.getInstance().getTime();
		java.sql.Date dat =new java.sql.Date(sqdate.getTime());
		
		boolean error = false;
		
		if(!password.equals(conpassword)){
			req.setAttribute("password", "The passwords you entered don't match!");
			error = true;
			/*RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
			rd.forward(req, res);		*/	
		}
		
		if(gender.equals(null) || gender.equals("")){
			
			req.setAttribute("gender", "A gender must be selected!");
			error =true;
			/*RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
			rd.forward(req, res);	*/				
		}
		
		if(location.equals(null) || location.equals("")){			
			
			req.setAttribute("location", "A location must be selected!");
			error =true;
			/*RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
			rd.forward(req, res);*/
		}
		
		if(error){
			RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
			rd.forward(req, res);
		}
		else{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","fyusuf","1071");
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Mail_User WHERE email=?");
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				boolean st = rs.next();
				if(!st){
				ps = con.prepareStatement("INSERT INTO Mail_User(FIRSTNAME,"
						+ "LASTNAME,EMAIL,PASSWORD,GENDER,LOCATION,REGISTRATIONDATE) VALUES(?,?,?,?,?,?,?)");
				ps.setString(1,firstname);
				ps.setString(2,lastname);
				ps.setString(3,email);
				ps.setString(4, MD5Encryption.getPassword(password));
				ps.setString(5, gender);
				ps.setString(6, location);
				ps.setDate(7, dat);
				
					int status = ps.executeUpdate();
					if(status > 0){
						req.setAttribute("signup", "You have succesfully signed up!");
						RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
						rd.forward(req, res);
					}
					else{
						req.setAttribute("signuperror", "Sorry, Sign up failed!");
						RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
						rd.forward(req, res);
					}
				
				}
				else{
					req.setAttribute("signuperror", "Email already exists!");
					RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
					rd.include(req, res);					
				}
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			}		
		}

}
