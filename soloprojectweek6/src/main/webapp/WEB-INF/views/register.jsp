<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<link rel = "stylesheet" href="resources/projectcss.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel = "stylesheet" href="resources/projectcss.css" />
 <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet"  />
<title>Register</title>
</head>
<body>

	 <div class="header" >
	<jsp:include page="header.jsp" />
</div>

	<br>
	<h1>Register</h1>

	<sf:form action="submitRegistration" method="POST"
		modelAttribute="customer">

		<sf:label path="username"> Username </sf:label>
		<sf:input type="text" path="username" />
		<br>
		<sf:label path="password"> Password </sf:label>
		<sf:input type="password" path="password" />
		<br>
		<sf:label path="confirmPassword"> Confirm Password </sf:label>
		<sf:input type="password" path="confirmPassword" />
		<br>
		<sf:label path="email"> Email </sf:label>
		<sf:input type="email" path="email" />
		<br>
		<sf:label path="firstName"> First Name </sf:label>
		<sf:input type="text" path="firstName" />
		<br>
		<sf:label path="lastName"> Last Name </sf:label>
		<sf:input type="text" path="lastName" />
		<br>
		<input type="submit" name="commit" value="submit" />
	</sf:form>
	<br>
	<br>
	<B>${requestScope.message1}</B>
	<B>${requestScope.message2}</B>
	<a href="login">Login Page</a>
</body>
</html>