<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><html>
<head>
<link rel = "stylesheet" href="resources/projectcss.css" />
<meta charset="ISO-8859-1">
<title>List of Products</title>
 <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet"  />
</head>
<body>
 <div class="header" >
	<jsp:include page="header.jsp" />
</div>
	<h2>List of Products</h2>
	<br>
	<B>${requestScope.message}</B>
	<br>
	<TABLE>
		<tr>
			<th>Product ID</th>
			<th>Name</th>
			<th>Price</th>
			<th></th>

		</tr>
		<c:forEach var="eachProduct" items="${listOfProducts}">
			<tr>
				<td><c:out value="${eachProduct.productId}" /></td>
				<td><c:out value="${eachProduct.productName}" /></td>
				<td><c:out value="${eachProduct.price}" /></td>
				<td><sf:form
						action="addProductToBasket/${eachProduct.productId}">
						<input type="submit" name="commit" value="add" />
					</sf:form></td>

			</tr>
		</c:forEach>

	</TABLE>
</body>
</html>