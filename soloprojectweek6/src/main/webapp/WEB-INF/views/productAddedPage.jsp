<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" href="resources/projectcss.css" />
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet"  />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="header" >
	<jsp:include page="header.jsp" />
</div>
<B>${requestScope.message}</B>
</body>
</html>