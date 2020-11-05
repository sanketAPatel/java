<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
		

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">emp</a>
				</li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
	

		<div class="container">
			<h3 class="text-center"> Employee List</h3>
			<hr>
			<div class="container text-left">

		<a href="<%=request.getContextPath()%> /new" class="btn btn-success">AddNew Employee</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>DEpartment</th>
						<th>Position</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="listEmp" items="${listEmp}">

						<tr>
							<td><c:out value="${emp.id}" /></td>
							<td><c:out value="${emp.name}" /></td>
							<td><c:out value="${emp.position}" /></td>
							<td><c:out value="${emp.department}" /></td>
							
							<td><a href="edit?id=<c:out value='${emp.id}' />">edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								
								<a href="delete?id=<c:out value='${emp.id}' />">Delete</a>
								
								</td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
