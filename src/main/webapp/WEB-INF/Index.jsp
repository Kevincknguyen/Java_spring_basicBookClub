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
	<div>
		<h1>Welcome, <c:out value="${currentUser.name}" /></h1>
	</div>
	<div>
		<form action="/logout" method="post">
		<button type="submit">Logout</button>
		</form>
		<a href="/addnewbook">Add book to my shelf!</a>
	
	</div>

	<div>
		<table class="table">
		   <thead class="thead-dark">
		       <tr>
		       		<th>DB ID</th>
		           <th>Title</th>
		           <th>Author</th>
		           <th>Posted by:</th>
		       </tr>
		   </thead>
		   <tbody>
		   
		   
		   <c:forEach var="book" items="${books}">
		   
		   
		       <tr>
		       		<td><c:out value="${book.id }"/></td>
		           <td>
		           		<a href="/showbook/${book.id }"><c:out value="${book.title}"/></a>
		           </td>
		          	<td><c:out value="${book.author}"/></td>
		           <td><c:out value="${book.user.name}"/></td>
		           <c:if test="${currentUser.id == book.user.id }">
		           <td><a href="/editbook/${book.id }">Edit this book</a></td>
		           </c:if>
		           <c:if test="${currentUser.id != book.user.id }">
		           		<td>---</td>
		           </c:if>
		          
		             
		       </tr>
		       
		       
		   </c:forEach>
		       
		       
		   </tbody>
		</table>
	
	
	
	</div>






</div>
						



</body>
</html>