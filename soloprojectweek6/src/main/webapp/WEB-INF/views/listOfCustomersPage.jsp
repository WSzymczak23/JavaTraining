<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="resources/projectcss.css" />
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet" />
<meta charset="ISO-8859-1">
<title>List of Customers</title>
</head>
<body>

	<div class="header">
		<jsp:include page="header.jsp" />
	</div>
	<h2>List of Customers</h2>

	<TABLE>
		<tr>
			<th>Username</th>
			<th>email</th>
			<th>First name</th>
			<th>Last name</th>
		</tr>
		<c:forEach var="eachItem" items="${listOfCustomerDetails}">
			<TR>
				<TD><c:out value="${eachItem.username}" /></TD>
				<TD><c:out value="${eachItem.email}" /></TD>
				<TD><c:out value="${eachItem.firstName}" /></TD>
				<TD><c:out value="${eachItem.lastName}" /></TD>
		</c:forEach>
	</TABLE>
</body>
</html>