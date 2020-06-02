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
			<h2>hey new bees register here</h2>
				<span style="color: red;"><form:errors path="user.*"/></span>
			
		<form:form action="/register" method="post" modelAttribute="user">
		<div class="row">
			<div class="input-field col s6">
				<form:label path="firstName" />First Name
				<form:input path="firstName"/>
			</div>
			<div class="input-field col s6">
				<form:label path="lastName" />Last Name
				<form:input path="lastName"/>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s6">
				<form:label path="email" />Email
			<form:input path="email"/>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s5">
				<form:label path="city" />City
			<form:input path="city"/>
			</div>
			
			<div class="input-field col s5">
				<form:label path="zipcode" />ZipCode
			<form:input path="zipcode"/>
			</div>
			<div class="input-field col s2">
				<form:label path="state" />State
			<form:input path="state"/>
			</div>
		</div>
		
			
			
		<div class="row">
			<div class="input-field col s6">
						<form:label path="password" />Password
			<form:input type="password" path="password"/>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
					<form:label path="passwordConfirmation" />Password Confirmation
					<form:input type="password" path="passwordConfirmation"/>	
			</div>
		</div>	
	
		
	 	<button class="btn waves-effect waves-light" type="submit" name="action">Register!
		  </button>
		
		</form:form>
		<br>
		<a href="/login">Already a member?</a>
	</div>
	
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>