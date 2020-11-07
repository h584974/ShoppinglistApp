<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
</head>
<body>
	<c:forEach items="${shoppinglists}" var="sl">
		<form action="ShoppinglistPost" method="post">
				<p>${sl.title}</p>
				<input type="hidden" name="list_id" value="${sl.List_ID}">
				<input type="submit" value="View">
		</form>
		<br>
	</c:forEach>
</body>
</html>