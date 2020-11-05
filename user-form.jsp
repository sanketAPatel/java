<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>

`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`


<html>
<head>
<title>HR Management </title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">emp</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${emp != null}">
					<form action="update" method="post">
				</c:if>
				
				
				<c:if test="${emp == null}">
					<form action="insert" method="post">
				</c:if>
				
				

				<caption>
					<h2>
						<c:if test="${emp != null}">
            			Edit emp
            		</c:if>
						<c:if test="${emp == null}">
            			Add New emp
            		</c:if>
					</h2>
				</caption>
				

				<c:if test="${emp != null}">
<input type="hidden" name="id" value="<c:out value='${emp.id}' />" />
				</c:if>

				
				<fieldset class="form-group">
		          <label>employee name</label>
<input type="text" value="<c:out value='${emp.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
		<label>employee position</label>
	 <input type="text" value="<c:out value='${emp.position}' />" class="form-control"
						name="position">
				</fieldset>

				
				<fieldset class="form-group">
			<label>employee department</label> 
	<input type="text" value="<c:out value='${emp.department}' />" class="form-control"
	               name="department">
				</fieldset>

				
				
				<button type="submit" class="btn btn-success">Save</button>
				
				
				</form>
			</div>
		</div>
	</div>
</body>
</html>
