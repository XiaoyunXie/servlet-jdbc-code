<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>index.jsp</h1>
<c:forEach items="${list }" var="tmp">
	<c:forEach items="${tmp }" var="t">
		<c:out value="${t}，"></c:out>
	</c:forEach><hr>
	
</c:forEach>
</body>
</html>