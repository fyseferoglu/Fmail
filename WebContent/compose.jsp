<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fmail.dao.MailDao" %>	
<!DOCTYPE html>
<html lang="en">
<head>
<title>Fmail</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="resources/icons/mail.ico">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link href="resources/css/compose.css" rel="stylesheet">
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
				<h4 style="color:#428bca"><strong>Fmail</strong></h4>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="home.jsp"><span class="glyphicon glyphicon-home"></span> Home </a></li>
					<li><a href="inbox.jsp"><span class="glyphicon glyphicon-inbox"></span> Inbox<span class="badge"><%=MailDao.getNumberOfMail((String)session.getAttribute("user")) %></span></a></li>
					<li class="active"><a href="compose.jsp"><span class="glyphicon glyphicon-envelope"></span> Compose mail </a></li>
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
				
				<div class="panel-group">
   					 <div class="panel panel-default">
     					 <div class="panel-heading panel-heading-custom"><h4>New Message</h4></div>
      					 <div class="panel-body">
      					 	<div>
      					 		 <form role="form" action="compose" method="post">
    								<div class="form-group">
    									<%
    										if(request.getAttribute("compose") != null){
    											String msg = (String) request.getAttribute("compose");
	    										out.print("<p style='color:green;'>" + msg + "</p></font>");    											
    										}
	    									if(request.getAttribute("composeerror") != null){
	    										String error = (String) request.getAttribute("composeerror");
	    										out.print("<p style='color:red;'>" + error + "</p></font>");
	    									}	    									
    									%>
      									<label for="to">To:</label>
      									<input type="email" class="form-control" name="to">
    								</div>
    								<div class="form-group">
      									<label for="subject">Subject:</label>
      									<input type="text" class="form-control" name="subject">
   									 </div>
   									<div>
   										 <label for="message">Message:</label>
      									 <textarea class="form-control" rows="10" name="message"></textarea>
   									</div> 
   									 <button type="submit" class="btn btn-primary">Submit</button>
  								 </form>     					 	
      					 	</div>     					 
      					 </div>
   					 </div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>

