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
		<div>
			<form action="/logout" method="post">
			<button type="submit">Logout</button>
			</form>
			<a href="/addnewbook">Add book to my shelf!</a>
		
		</div>
	</div>



	<!--  TABLE FOR ALL AVAILABLE BOOKS TO BORROW i.e NO BORROW ID-->
	<div>
	<h1>Available books to borrow</h1>
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
		           <td><a href="/showbook/${book.id }"><c:out value="${book.title}"/></a></td>
		           <td><c:out value="${book.author}"/></td>
		           <td><c:out value="${book.user.name}"/></td>
			           <c:if test="${currentUser.id == book.user.id }">
							  <td>
							      <a href="/editbook/${book.id }">Edit this book</a>
								  <form action="/deletebook/${book.id}" method="post">
										<input type="hidden" name="_method" value="delete">
										<input type="submit" value="Delete">
								  </form>
					           </td>
			           </c:if>
			           
			           <c:if test="${currentUser.id != book.user.id }">
			           		<td>
			           		<form:form action="/api/borrow/${book.id}" method="post" modelAttribute="book">
								<input type="hidden" name="_method" value="put">
								<form:input type="hidden" path="borrow" value="${currentUser.id}" />
				           		<input type=submit value="Borrow"/>
			           		</form:form>
	
			           		</td>
			          </c:if> 
		       </tr>
		   </c:forEach>
		   </tbody>
		</table>
	 </div>
	
	
	
	<!--  TABLE FOR ALL BOOKS I AM BORROWING i.e BORROW ID= SESSION ID -->
	
	<div>
		<h1>Books that I myself am borrowing</h1>
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
	
		   <c:forEach var="book" items="${borrow}">
		       <tr>
		       	    <td><c:out value="${book.id }"/></td>
		            <td><a href="/showbook/${book.id }"><c:out value="${book.title}"/></a> </td>
		          	<td><c:out value="${book.author}"/></td>
		            <td><c:out value="${book.user.name}"/></td>
		     
	           		<td>
	           		<form:form action="/api/borrow/${book.id}" method="post" modelAttribute="book">
						<input type="hidden" name="_method" value="put">
						<form:input type="hidden" path="borrow" value="${null}" />
		           		<input type=submit value="Return"/>
	           		</form:form>
	           		</td>
		       </tr>
		       
		       
		   </c:forEach>
		       
		    
		   </tbody>
		</table>
	</div>


	<!--  TABLE FOR ALL BOOKS I AM WROTE i.e USER ID= SESSION ID -->
	<div>
		<h1>Books to my name</h1>
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
			<c:forEach var="book" items="${myBooks}">
		       <tr>
		           <td><c:out value="${book.id }"/></td>
		           <td><a href="/showbook/${book.id }"><c:out value="${book.title}"/></a></td>
		           <td><c:out value="${book.author}"/></td>
		           <td><c:out value="${book.user.name}"/></td>
		           <td>
		               <a href="/editbook/${book.id }">Edit this book</a>
					   <form action="/deletebook/${book.id}" method="post">
							<input type="hidden" name="_method" value="delete">
							<input type="submit" value="Delete">
					   </form>
		           </td>  
		       </tr>
			</c:forEach>
			</tbody>
		</table>
	</div>







</div>
</body>
</html>