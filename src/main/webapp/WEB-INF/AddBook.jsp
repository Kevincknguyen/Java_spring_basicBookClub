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


<div style="display:flex; justify-content:space-between; margin:2%;">
		<h1>Title</h1>
		<div><a href="http://localhost:8080/index">return home</a></div>
	
	</div>

<form:form action="/api/newBook" method="post" modelAttribute="book">
			
			<p>
					<form:label path="title">Title</form:label>
					<form:errors path="title" class="text-danger"/>
					<form:input path="title" />
			</p>
			<p>
					<form:label path="author">Author</form:label>
					<form:errors path="author" class="text-danger"/>
					<form:input path="author" />
			</p>
			<p>
					<form:label path="thoughts">Thoughts</form:label>
					<form:errors path="thoughts" class="text-danger"/>
					<form:input type="textarea" path="thoughts" />
			</p>
			<p>
						<form:input type="hidden" path="user" value="${currentUser.id}" />
						<input type=submit value="Submit"/>
					</p>
		</form:form>
</body>
</html>