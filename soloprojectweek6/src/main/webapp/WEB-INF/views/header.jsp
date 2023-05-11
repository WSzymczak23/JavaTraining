<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<link rel = "stylesheet" href="resources/projectcss.css" />
<meta charset="ISO-8859-1">
 <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet"  />
<title>Insert title here</title>
</head>
<body>
	<h1>Hi, ${sessionScope.username}</h1>
	<p><sf:form action="logout">
						<input type="submit" name="commit" value="Log Out" />
					</sf:form>
					</p>
	
<div class="box"><a href="${pageContext.request.contextPath}/welcome">Welcome page    </a></div>
<div class="box"><a href="${pageContext.request.contextPath}/basket">Show basket   </a></div>
<div class="box"><a href="${pageContext.request.contextPath}/listOfProducts">Products catalogue   </a></div>
<div class="box"><a href="${pageContext.request.contextPath}/listOfOrders">List of Orders   </a></div>
<div class="box"><a href="${pageContext.request.contextPath}/listOfCustomers">Customer details   </a></div>
<div class="box"><a href="${pageContext.request.contextPath}/listOfPayments">List of Payments</a></div>
</body>
</html>