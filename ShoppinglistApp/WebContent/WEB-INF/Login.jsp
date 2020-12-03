<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<link rel="stylesheet"
	href="CSS/LoginStyle.css">
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<form action="Login" method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="username">Username:</label>
				<input type="text" name="username" value="${username}" placeholder="Username" pattern=".{3,20}" required>
				<font style="color: red">${errorUsername}</font>
			</div>
			<div class="pure-control-group">
				<label for="password">Password:</label>
				<input type="password" name="password" placeholder="Password" pattern=".{8,64}" required>
				<font color="red">${errorPassword}</font>
			</div>
			<div class="pure-controls">
				<input type="submit" name="login" value="Login" class="pure-button pure-button-primary">
				<input type="submit" form="create" value="Create Account" class="pure-button pure-button-primary">
			</div>
		</fieldset>
	</form>
	<form action="CreateAccount" method="get" id="create"></form>
</body>
</html>