<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/projectcss.css" />
<meta charset="ISO-8859-1">
<title>Basket</title>
</head>
<body>

	<div class="header">
		<jsp:include page="header.jsp" />
	</div>
	<h1>Items in your basket</h1>

	<B>${requestScope.message}</B>
	<br>
	<br>
	<c:forEach var="eachEntry" items="${basket}">
		<table>
			<tr>
				<th>Product ID</th>
				<th>Product name</th>
				<th>Amount</th>
			</tr>
			<tr>
				<th><c:out value="${eachEntry.key.productId}" /></th>
				<th><c:out value="${eachEntry.key.productName}" /></th>
				<th><c:out value="${eachEntry.value}" /></th>
			</tr>
		</table>
	</c:forEach>
	<a href="${pageContext.request.contextPath}/clearBasket"> Clear
		Basket </a>
	<br>
	<a href="${pageContext.request.contextPath}/placeOrder"> Place
		Order </a>
</body>
</html>