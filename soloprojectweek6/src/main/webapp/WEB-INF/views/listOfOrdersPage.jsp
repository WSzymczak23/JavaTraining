<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="resources/projectcss.css" />
<meta charset="ISO-8859-1">
<title>List of Orders</title>
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet" />
</head>
<body>
	<div class="header">
		<jsp:include page="header.jsp" />
	</div>
	<h2>Your Orders</h2>

	<TABLE>
		<tr>
			<th>orderId</th>
			<th>price</th>
			<th>order date</th>
			<th>username</th>

			<c:forEach var="eachOrder" items="${listOfCustomerOrders}">
				<TR>
					<TD><c:out value="${eachOrder.orderId}" /></TD>
					<TD><c:out value="${eachOrder.price}" /></TD>
					<TD><c:out value="${eachOrder.orderDate}" /></TD>
					<TD><c:out value="${eachOrder.customer.username}" /></TD>
			</c:forEach>
	</TABLE>
</body>
</html>