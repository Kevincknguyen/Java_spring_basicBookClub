<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div>

	<div style="display:flex; justify-content:space-between; margin:2%;">
		<h1><c:out value="${book.title}"/></h1>
		<div><a href="http://localhost:8080/index">return home</a></div>
	
	</div>
	
	
	
	<div>
		<p>
			<span style="color:red">
				<c:if test="${currentUser.id == book.user.id }">You</c:if>
				<c:if test="${currentUser.id != book.user.id }"><c:out value="${book.user.name}"/></c:if>
			</span> 
		read <span style="color:purple;"><c:out value="${book.title}"/></span> by <span style="color:green;"><c:out value="${book.author}"/></span> 
		</p>
		
		<p>Here are <c:if test="${currentUser.id == book.user.id }">Your</c:if>
				<c:if test="${currentUser.id != book.user.id }"><c:out value="${book.user.name}"/></c:if> thoughts</p>
	</div>

	<div style="border-style:solid; border-width:2px 0px;">
	<c:out value="${book.thoughts}"/>
	</div>
	<c:if test="${currentUser.id == book.user.id }"><a href="http://localhost:8080/editbook/${book.id}">Edit</a></c:if>

















</div>
</body>
</html>