<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

</head>
<body>
	
	 <div class="container ">
	 <div class="row">
		 <div class="col s3">
		 		 <img alt="beautyBook" src="/images/logo.png">
		 </div>
		 <br>
		 <div class="right-align">
		 	<a href="/register">Register | </a> 
		 	<a href="/login">Login</a>
		 </div>
		 <div class="col s6">	
		 	<h2>Grow Gorgeous..</h2>
		 </div>
	 </div >
	 <div class="row card-panel amber lighten-3">
	 	<div class="col s8">
	 		<h4>Know your face shape, Groom your eyebrows!</h4>
	 	<p>Eyebrows have been in the spotlight more than ever recently, and for good reason.
	 	 Well-groomed brows act to frame the eyes and highlight your best features.
	 	  In fact, strong brows can make you look put-together on a makeup free day. The 
	 	right eyebrow shape can brighten your appearance and make you look more youthful.
	 	</p>
	 	<br>
	 	<p> We are here to help. Sign Up for free!</p>
	 	</div>
	 	<div class="col s4 right-align">
	 	</div>
	 </div>
	 <div class="center-align">
	 	<h3>Your eyes speak louder than words!!</h3>
	 	<img alt="" src="/images/lashes.gif" width="60%">
	 	<p>Eyebrows have been in the spotlight more than ever recently, and for good reason.
	 	 Well-groomed brows act to frame the eyes and highlight your best features.
	 	  In fact, strong brows can make you look put-together on a makeup free day. The 
	 	right eyebrow shape can brighten your appearance and make you look more youthful.
	 	</p>
	 </div>
	 	
	</div>
	 

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js">
$(document).ready(function(){
    $('.carousel').carousel();
  });
      
 </script>

</body>
</html>