<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List, java.sql.*, com.fmail.dao.MailDao, com.fmail.bean.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  	
<!DOCTYPE html>
<html lang="en">
<head>
<title>Fmail</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="resources/icons/mail.ico">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link href="resources/css/inbox.css" rel="stylesheet">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$(".clickable-row").click(function(e) {
		window.document.location = $(this).data("href");
    });
	$(".btn-default").click(function(){
		window.location = "search.jsp?q=" + $(".form-control").val();
				
	});
});
</script>
</head>
<body>

	<%		
		if (session.getAttribute("user") == null) {			
			request.setAttribute("Error1", "Please, login first");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);			
		}
		
	%>

	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<h4 style="color: #428bca"><strong>Fmail</strong></h4>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="home.jsp"><span class="glyphicon glyphicon-home"></span> Home </a></li>
					<li><a href="inbox.jsp"><span class="glyphicon glyphicon-inbox"></span> Inbox<span class="badge"><%=MailDao.getNumberOfMail((String)session.getAttribute("user")) %></span></a></li>
					<li><a href="compose.jsp"><span class="glyphicon glyphicon-envelope"></span> Compose Mail </a></li>
					<li><a href="sentmail.jsp"><span class="glyphicon glyphicon-share"></span> Sent Mail </a></li>
					<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Log out </a></li>
				</ul>
				<br>
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search Mail..">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</div>

			<div class="col-sm-9">
				<h4>
					<small>Mail</small>
				</h4>			
				<hr>
				<%
					if(request.getAttribute("delete") != null){
						String msg = (String) request.getAttribute("delete");
						out.print("<p style='color:red;'>" + msg + "</p></font>");
					}
				
					if(request.getAttribute("deleteError") != null){
						String error = (String) request.getAttribute("deleteError");
						out.print("<p style='color:red;'>" + error + "</p></font>");
					}
				
				%>
				<%  
					String id= null;
					Mail m = null;
					if(session.getAttribute("user") != null){
						id = request.getParameter("id");
						m=MailDao.getRecordById(Long.parseLong(id));  		
					}		
				%> 
			
				<div class="panel panel-default">
					<div class="panel-heading panel-heading-custom">
						<h4>From: <%=m.sender %></h4>
						<h4>To: <%=m.receiver %></h4>
						<h4>Subject: <%=m.subject %></h4>
						<h5> <span class="glyphicon glyphicon-time"></span> <%=m.sendingdate + " " + m.sendingtime %></h5>					
					</div>
				  	<div class="panel-body panel-body-custom"><%=m.message %></div>
				</div>
			
			</div>
		</div>
	</div>

	

</body>
</html>

