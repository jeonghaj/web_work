<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    List<String> todo = new ArrayList<>();
    todo.add("html �����ϱ�");
	todo.add("css �����ϱ�");
	todo.add("javascript �����ϱ�");
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