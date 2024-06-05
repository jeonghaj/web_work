<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    List<String> todo = new ArrayList<>();
    todo.add("html 공부하기");
	todo.add("css 공부하기");
	todo.add("javascript 공부하기");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<ul>
	<% for(String tmp:todo){%>
		<li><%=tmp %></li>
	<%} %>
	
	</ul>
</body>
</html>