<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat, com.fmail.dao.MailDao" %>	
<!DOCTYPE html>
<html lang="en">
<head>
<title>Fmail</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="resources/icons/mail.ico">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
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
<style>
/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
.row.content {
	height: 1000px
}

/* Set gray background color and 100% height */
.sidenav {
	background-color: #d9eae8;
	height: 100%;
}

h4{
	font-family: Times New Roman;

}

.well{
	background-color: #d9eae8;
}


/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>
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
					<li class="active"><a href="home.jsp"><span class="glyphicon glyphicon-home"></span> Home </a></li>
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
					<small>RECENT POSTS</small>
				</h4>			
				<hr>
				<div class="well">
				<h4>Welcome <%=session.getAttribute("user")%></h4>
				<p><i>Login Date: <% 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");			
				out.print(dateFormat.format(new Date(session.getCreationTime()))); %></i></p>
				</div>				
			</div>
		</div>
	</div>

	

</body>
</html>

