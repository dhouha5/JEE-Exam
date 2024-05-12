<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: blue">

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link" style="color: white">Users</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="insert" method="post">
                <caption>
                    <h2>Add New User</h2>
                </caption>

                <fieldset class="form-group">
                    <label>User Name</label>
                    <input type="text" class="form-control" name="uname" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Password</label>
                    <input type="password" class="form-control" name="upwd" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Email</label>
                    <input type="text" class="form-control" name="uemail">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Mobile</label>
                    <input type="text" class="form-control" name="umobile">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Role</label>
                    <input type="text" class="form-control" name="role">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
