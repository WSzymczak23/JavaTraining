<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="resources/projectcss.css" />
<meta charset="ISO-8859-1">
<title>List of Payments</title>
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet" />
</head>
<body>
	<div class="header">
		<jsp:include page="header.jsp" />
	</div>
	<h2>List of Payments</h2>
	<TABLE>
		<tr>
			<th>Payment ID</th>
			<th>Amount</th>
			<th>Order ID</th>
			<th></th>
		</tr>
		<c:forEach var="eachItem" items="${listOfCustomerPayments}">
			<TR>
				<TD><c:out value="${eachItem.paymentId}" /></TD>
				<TD><c:out value="${eachItem.amount}" /></TD>
				<TD><c:out value="${eachItem.order.orderId}" /></TD>
		</c:forEach>
	</TABLE>
</body>
</html>