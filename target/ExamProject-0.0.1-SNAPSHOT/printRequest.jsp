<%@page import="tn.iit.printModels.Subject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.SQLException"%>


<html>
<head>
<title>Add Print Request</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="#" class="navbar-brand">Print Request Management</a>
			</div>
		</nav>
	</header>
	<br>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="printRequest" method="post"
					enctype="multipart/form-data">
					<caption>
						<h2>Add New Print Request</h2>
					</caption>
					<% List<Subject> subjects = (List<Subject>)request.getAttribute("listSubject"); %>
					<fieldset class="form-group">
						<label>Select Subject</label> <select class="form-control"
							name="subjectId">
							<%
							for (Subject subject : subjects) {
							%>
							<option value="<%=subject.getSubject_id()%>"><%=subject.getSubject_name()%></option>
							<%
							}
							%>
						</select>
					</fieldset>

					<fieldset class="form-group">
						<label>Arrival Date</label> <input type="date"
							class="form-control" name="arrivalDate" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Upload Document</label> <input type="file"
							class="form-control-file" name="documentName" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Number of Copies</label> <input type="number"
							class="form-control" name="numCopies" required="required">
					</fieldset>

					<button type="submit" class="btn btn-success">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

