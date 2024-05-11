<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="tn.iit.printModels.User" %>

<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
            <div>
                <a href="https://www.xadmin.net" class="navbar-brand"> User Management Application </a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div class="row">
        <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

        <div class="container">
            <h3 class="text-center">List of Users</h3>
            <hr>
            <div class="container text-left">

                <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Mobile</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        Object attribute = request.getAttribute("listUser");
                        if (attribute instanceof List) {
                            List<User> listUser = (List<User>) attribute;
                            for (User user : listUser) {
                    %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= user.getUname() %></td>
                        <td><%= user.getUemail() %></td>
                        <td><%= user.getUmobile() %></td>
                        <td><%= user.getRole() %></td>
                        <td>
                            <a href="edit?id=<%= user.getId() %>">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="delete?id=<%= user.getId() %>">Delete</a>
                        </td>
                    </tr>
                    <% 
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
