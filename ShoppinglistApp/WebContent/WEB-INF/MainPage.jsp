<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>
	<form action="ShoppinglistPost" method="post">
		<c:forEach items="${shoppinglists}" var="sl">
				<p>${sl.title}</p>
				<input type="hidden" name="list_id" value="${sl.list_id}">
				<input type="submit" value="View">
				<br>
		</c:forEach>
	</form>
</body>
</html>