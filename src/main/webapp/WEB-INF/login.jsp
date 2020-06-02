<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

</head>
<body>
	<div class="container">
		<h2>Login</h2>
		<p><c:out value="${error}" /></p>
	    <form method="post" action="/login">
	       <div class="row">
				<div class="input-field col s6">
					  <label for="email">Email</label>
	            	<input type="text" id="email" name="email"/>
				</div>
			</div>
			 <div class="row">
				<div class="input-field col s6">
					 
	            <label for="password">Password</label>
	            <input type="password" id="password" name="password"/>
				</div>
			</div>		
	      
			<button class="btn waves-effect waves-light" type="submit" name="action">Login!
			 </button>    
		</form>
		<br>
		<a href="/register">New to beautyBook?</a>    
	</div>  
	
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	
</body>
</html>