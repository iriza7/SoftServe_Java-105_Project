<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Main Page With Funny Users</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">
	<h1>List of Users</h1>
	

	<hr />

	<table class='table'>
		<thead>
			<tr>
				<th>Num</th>
				<th>Username</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Email</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.getUserId()}</td>
					<td>${user.getUserName() }</td>
					<td>${user.getFirstName()}</td>
					<td>${user.getLastName() }</td>
					<td>${user.geteMail() }
					<td><a href="userEdit/${user.userId}">EDIT</a></br></td>
					<td><a href="userdel/${user.userId}">DELETE</a></br></td>


				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr />
</div>
<!-- </body> -->
<!-- </html> -->