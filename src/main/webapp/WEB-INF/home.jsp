<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<style type="text/css">
	.form-container {
	  max-width: 550px;
	  max-height: 450px;
	  padding: 10px;
	  background-color: white;
	   opacity: 1;
	 
	}
	.form-popup {
	 background-color: white;
	  display: none;
	  position: fixed;
	  bottom: 270px;	 
	  right: 500px;
	  left: 200px;
	  border: 3px solid #f1f1f1;
	  opacity: 1;
	  z-index: 10;
	}
	.displayedImage{
		width:300px;
		height: 200px;
	}
</style>
</head>
<body>
	<div class="container">
		<a href="/logout">Logout</a> <br>
		
		<h1> <c:out value="${message }"></c:out>, <c:out value="${user.firstName }"></c:out>!</h1>
		<a href="/face/${user.id}">Brow Shape</a> | <a> Articles </a>
		<br>
		<span style="color: red;"><form:errors path="newPost.*"/></span>
		
		<button class="btn waves-effect waves-light" onClick="openFormTips()"  name="action">Share my tips
		 </button>
	 	<button class="btn waves-effect waves-light" type="submit" onClick="openFormImg()" name="action">Share my image
	 	</button>
	 	
		<div class="form-popup" id="tipsForm">
			<p>Share your thoughts, ideas, experience about any beauty topics. Topics can vary from 
			eyebrows, eye lashes, skin care and so on.
			</p>
		  	<form:form class="form-container" action="/home" method="post" modelAttribute="newPost" id="tipsForm">
				<form:label path="content"></form:label>
				<form:input path="content" type="text"/>
				<form:input type="hidden" path="user" value="${user.id }"/>
			
			    <button type="submit" class="btn">Share</button>
			    <button type="button" class="btn cancel" onclick="closeFormTips()">Cancel</button>
		  	</form:form>
		</div>	
		
		<div class="form-popup" id="imgForm">
			<p>Share Pictures!</p>
		  	<form:form class="form-container" action="/uploadImage" enctype="multipart/form-data" method="post">
				<input type="file" name="image"/>

			    <button type="submit" class="btn">Share</button>
			    <button type="button" class="btn cancel" onclick="closeFormImg()">Cancel</button>
		  	</form:form>
		</div>	
		
		<div class="row">
			
			
		
			 	
			<div class="row">
				<div class="col s8">
					<c:forEach items="${allPosts }" var="p">
						<p>Tips by: <c:out value="${p.user.firstName }"></c:out> </p>
						<c:out value="${p.content }"></c:out>
						
						<a href="/like/${p.id }">Useful</a> 
						<c:if test="${p.likes != null }">
							<c:out value="${p.likes }"></c:out>
						</c:if>
						 <br>
						 
						<ul>
							<c:forEach items="${p.getComment()}" var="c">
							<li><c:out value="${c.content}"></c:out></li>
						</c:forEach>
						</ul>
					
						<form:form action="/comment" method="post" modelAttribute="newComment">
							<form:input path="content"/>
							<form:input path="user" type="hidden" value="${user.id}"/>
							<form:input path="bpost" type="hidden" value="${p.id }"/>
							<input type="submit" value="Comment">
						</form:form>
					</c:forEach>
					
					<c:forEach var="ui" items="${userImages}">
						<img class="displayedImage" src="/image/${ui.id}" />
					</c:forEach>
				</div>
			</div>
		</div>
		
	</div>
		

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script>
		function openFormTips() {
		  document.getElementById("tipsForm").style.display = "block";
		}
		
		function closeFormTips() {
		  document.getElementById("tipsForm").style.display = "none";
		}
		function openFormImg() {
			  document.getElementById("imgForm").style.display = "block";
		}
		
		function closeFormImg() {
		  document.getElementById("imgForm").style.display = "none";
		}
	</script>
</body>
</html>