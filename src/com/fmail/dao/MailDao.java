package com.fmail.dao;
import com.fmail.bean.Mail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MailDao {
	
	public static Connection getConnection(){  
	    Connection con=null;  
	    try{  
	        Class.forName("oracle.jdbc.driver.OracleDriver");  
	        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","FYUSUF","1071");  
	    }catch(Exception e){e.printStackTrace();}  
	    return con;  
	} 
	
	
	public static List<Mail> getAllRecords(String receiver){  
	    List<Mail> list=new ArrayList<Mail>();  
	      
	    try{  
	        Connection con=getConnection();  
	        PreparedStatement ps=con.prepareStatement("select * from inbox where receiver=? order by sendingtime,sendingdate");
	        ps.setString(1, receiver);
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            Mail m=new Mail();  
	            m.setMailid(rs.getLong("mailid"));
	            m.setSender(rs.getString("sender"));
	            m.setReceiver(rs.getString("receiver"));
	            m.setSubject(rs.getString("subject"));
	            m.setMessage(rs.getString("message"));
	            m.setSendingdate(rs.getString("sendingdate"));
	            m.setSendingtime(rs.getString("sendingtime"));
	            list.add(m);  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	public static List<Mail> getAllSentMail(String from){  
	    List<Mail> list=new ArrayList<Mail>();  
	      
	    try{  
	        Connection con=getConnection();  
	        PreparedStatement ps=con.prepareStatement("select * from inbox "
	        		+ "where sender=? order by sendingdate desc,sendingtime desc");
	        ps.setString(1, from);
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            Mail m=new Mail();  
	            m.setMailid(rs.getLong("mailid"));
	            m.setSender(rs.getString("sender"));
	            m.setReceiver(rs.getString("receiver"));
	            m.setSubject(rs.getString("subject"));
	            m.setMessage(rs.getString("message"));
	            m.setSendingdate(rs.getString("sendingdate"));
	            m.setSendingtime(rs.getString("sendingtime"));
	            list.add(m);  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	public static Mail getRecordById(long id){  
	    Mail m=null;  
	    try{  
	        Connection con=getConnection();  
	        PreparedStatement ps=con.prepareStatement("select * from inbox where mailid=?");  
	        ps.setLong(1,id);  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            m=new Mail();  
	            m.setMailid(rs.getLong("mailid"));
	            m.setSender(rs.getString("sender"));
	            m.setReceiver(rs.getString("receiver"));
	            m.setSubject(rs.getString("subject"));
	            m.setMessage(rs.getString("message"));
	            m.setSendingdate(rs.getString("sendingdate"));
	            m.setSendingtime(rs.getString("sendingtime"));
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return m;  
	} 
	
	public static List<Mail> searchMail(String query,String to){  
	    List<Mail> list=new ArrayList<Mail>();  
	      
	    try{  
	        Connection con=getConnection();  
	        PreparedStatement ps=con.prepareStatement("select * from inbox where receiver='" + to 
	        		+ "' and(subject like '%"+ query +"%' or message like '%" + query + "%')"); 
	        	  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            Mail m=new Mail();  
	            m.setMailid(rs.getLong("mailid"));
	            m.setSender(rs.getString("sender"));
	            m.setReceiver(rs.getString("receiver"));
	            m.setSubject(rs.getString("subject"));
	            m.setMessage(rs.getString("message"));
	            m.setSendingdate(rs.getString("sendingdate"));
	            m.setSendingtime(rs.getString("sendingtime"));
	            list.add(m);  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	public static int getNumberOfMail(String receiver){
		
		int numOfMail=0;
		try{  
	        Connection con=getConnection();  
	        PreparedStatement ps=con.prepareStatement("select count(*) from inbox where receiver=?");
	        ps.setString(1, receiver);
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            numOfMail = rs.getInt(1);	           
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    
		return numOfMail;	
		
	}
	
	
}
