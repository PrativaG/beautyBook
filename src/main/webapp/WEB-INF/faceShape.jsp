<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<a href="/home">Back to Home</a> <br>
		<c:if test="${user.faceShape == null}">
		<h1>What is your face shape?</h1>
		<form action="/face/${user.id}" method="post">
		<input type="text" name="faceShape">
		<select name="faceShape">
			<option value="round">Round</option>
			<option value="oval">Oval</option>
			<option value="heart"> Heart</option>
			<option value="oblong">Oblong</option>
			<option value="square">Square</option>
			<option value="diamond">Diamond</option>
		</select>
		<button class="btn waves-effect waves-light" type="submit" name="action">Submit
		 </button>
		</form>
		</c:if>
		<h3>Your face shape is: <c:out value="${user.faceShape}"></c:out> </h3>
		<p>Here is the eyebrow shape suggestion for you, <c:out value="${user.firstName }"></c:out> ! </p>
		<c:set var="face1" value="round"></c:set>
		<c:if test="${ user.faceShape eq face1}">
		<p>For round face, it is good to make it not look rounder more. To create
		longer effect in face make your eyebrows to look more angled.
		</p>
		<img alt="" src="/images/sugg/hard.jpg" width="50%">
		<img alt="" src="/images/sugg/soft.jpg" width="50%">
		</c:if>
		
		<c:set var="face2" value="oval"></c:set>
		<c:if test="${ user.faceShape eq face2}">
		<p>For oval face, it is good to make it not look rounder more. To create
		longer effect in face make your eyebrows to look more angled.
		</p>
		<img alt="" src="/images/sugg/soft.jpg" width="50%">
		</c:if>
		
		<c:set var="face3" value="heart"></c:set>
		<c:if test="${ user.faceShape eq face3}">
		<p>For heart face, it is good to make it not look rounder more. To create
		longer effect in face make your eyebrows to look more angled.
		</p>
		<img alt="" src="/images/sugg/round.jpg" width="50%">
		</c:if>
		
		<c:set var="face4" value="sqaure"></c:set>
		<c:if test="${ user.faceShape eq face4}">
		<p>For heart face, it is good to make it not look rounder more. To create
		longer effect in face make your eyebrows to look more angled.
		</p>
		<img alt="" src="/images/sugg/round.jpg" width="50%">
		</c:if>
		
		<c:set var="face5" value="diamond"></c:set>
		<c:if test="${ user.faceShape eq face5}">
		<p>For heart face, it is good to make it not look rounder more. To create
		longer effect in face make your eyebrows to look more angled.
		</p>
		<img alt="" src="/images/sugg/round.jpg" width="50%">
		</c:if>
		
		<c:set var="face6" value="oblong"></c:set>
		<c:if test="${ user.faceShape eq face6}">
		<p>For heart face, it is good to make it not look rounder more. To create
		longer effect in face make your eyebrows to look more angled.
		</p>
		<img alt="" src="/images/sugg/round.jpg" width="50%">
		</c:if>
	</div>
	

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>