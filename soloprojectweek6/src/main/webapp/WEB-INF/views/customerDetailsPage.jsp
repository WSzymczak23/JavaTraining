<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><html>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<head>
<link rel="stylesheet" href="resources/projectcss.css" />
<meta charset="ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet" />
<title>Customer details</title>
</head>
<body>

	<div class="header">
		<jsp:include page="header.jsp" />
	</div>
	<br>
	<B>${requestScope.message}</B>
	
		<h2><a href="listOfOrders">Your orders</a></h2>
<h2><a href="listOfCustomers">Your account details</a></h2>
<h2><a href="listOfPayments">Your payments</a></h2>
</body>
</html>