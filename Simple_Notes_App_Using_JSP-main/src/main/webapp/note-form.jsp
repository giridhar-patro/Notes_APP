<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<html>
<head>
<title>Notes App</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: rgb(18, 49, 150)">
			<div>
				<a href="/" class="navbar-brand"> Notes App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Notes</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${note != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${note == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${note != null}">
            			Edit Note
            		</c:if>
						<c:if test="${note == null}">
            			Add New Note
            		</c:if>
					</h2>
				</caption>

				<c:if test="${note != null}">
					<input type="hidden" name="id" value="<c:out value='${note.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${note.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Title</label> <input type="text"
						value="<c:out value='${note.title}' />" class="form-control"
						name="title">
				</fieldset>

				<fieldset class="form-group">
					<label>Text</label> <input type="text"
						value="<c:out value='${note.text}' />" class="form-control"
						name="text">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
