<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
 <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet"  />
<link rel = "stylesheet" href="resources/projectcss.css" />
</head>

<body>
  <div class="header" >
	<jsp:include page="header.jsp" />
</div>

	<h1>Login</h1>

	<sf:form action="submitLogin" method="POST" modelAttribute="customer">
		<sf:label path="username"> Username </sf:label>
		<sf:input type="text" path="username" />
		<br>
		<sf:label path="password"> Password </sf:label>
		<sf:input type="password" path="password" />

		<input type="submit" name="commit" value="Submit" />
	</sf:form>
	
	<br>
	<B>${requestScope.message1}</B>
	<br>
	<B>${requestScope.message2}</B>
	<br>
	<B>${requestScope.message3}</B>
</body>
</html>