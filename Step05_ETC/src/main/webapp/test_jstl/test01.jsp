<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_jstl/test01.jsp</title>
</head>
<body>
	<h1>JSTL 반복문</h1>
	<c:forEach var="i" begin="1" end="10">
		<p>안녕 <strong>${i }</strong></p>
	</c:forEach>
	
	<h1>JSTL 반복문</h1>
	<c:forEach var="i" begin="1" end="10" step="2">
		<p>안녕 <strong>${i }</strong></p>
	</c:forEach>
</body>
</html>













