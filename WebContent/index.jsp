<!DOCTYPE html>
<html lang="en">
<head>

<title>Log in</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="resources/icons/mail.ico">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link href="resources/css/signin.css" rel="stylesheet">
<script src="resources/js/1.12.4/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<style type="text/css">

.container .img-circle{
	display: block;
  	margin-left: auto;
  	margin-right: auto;
}
</style>
</head>

<body>
	<div class="container">
		<form class="form-signin" action="login" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<img src="resources/icons/profil.jpg" class="img-circle" alt="Cinque Terre" width="96" height="96">
			<label for="inputEmail" class="sr-only">Email address</label> <input
				type="email" name="inputEmail" class="form-control"
				placeholder="Email address" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" name="inputPassword" class="form-control"
				placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign 
				in</button>
			Don't have an account? 	
			<a href="signup.jsp">Sign up now</a>
			<%
				if (request.getAttribute("Error1") != null) {
					String Error1 = (String) request.getAttribute("Error1");
					out.print("<center><font style='color:red'><h4>" + Error1 + "</h4></font></center>");
				}
			
				if(request.getAttribute("logout") != null){					
					String logout = (String) request.getAttribute("logout");
					out.print("<center><font style='color:red'><h4>" + logout + "</h4></font></center>");					
				}
				
				if(request.getAttribute("Error") != null){
					String error = (String) request.getAttribute("Error");
					out.print("<h4 style='color:red;text-align:center'>" + error + "</h4></font>");
				}
				if(request.getAttribute("signup") != null){
					String msg = (String) request.getAttribute("signup");
					out.print("<h4 style='color:red;text-align:center'>" + msg + "</h4></font>");
				}
				if(request.getAttribute("signuperror") != null){
					String msg = (String) request.getAttribute("signuperror");
					out.print("<h4 style='color:red;text-align:center'>" + msg + "</h4></font>");
				}
				
				if(session.getAttribute("user") != null){					
					response.sendRedirect("home.jsp");		
				}
			%>
		</form>
	</div>

</body>
</html>

