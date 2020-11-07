<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<link rel="stylesheet"
	href="CSS/CreateAccountStyle.css">
<meta charset="UTF-8">
<title>CreateAccount</title>
</head>
<body>
	<h1>Create Account</h1>
	<form action="CreateAccountPost" method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="username">Username:</label>
				<input type="text" name="username" value="${username}" placeholder="Username" pattern="^[0-9a-zA-ZæøåÆØÅ |_-]{3,20}$" required>
				<font color="red">${ERROR_USERNAME}</font>
			</div>
			<div class="pure-control-group">
				<label for="password">Password:</label>
				<input type="password" name="password" placeholder="Password" pattern=".{8,64}" required>
				<font color="red">${ERROR_PASSWORD}</font>
			</div>
			<div class="pure-control-group">
				<label for="password">Repeat Password:</label>
				<input type="password" name="passwordrepeat" placeholder="Repeat Password" pattern=".{8,64}" required>
				<font color="red">${ERROR_PASSWORD_REPEAT}</font>
			</div>
			<div class="pure-controls">
				<input type="submit" value="Create Account" class="pure-button pure-button-primary">
				<input type="submit" form="login" value="Login" class="pure-button pure-button-primary">
			</div>
		</fieldset>
	</form>
	<form id="login" action="Login" method="get"></form>
</body>
</html>