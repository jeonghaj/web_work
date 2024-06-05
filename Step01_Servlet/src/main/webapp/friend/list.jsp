<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    	List<String> names = new ArrayList<>();
    	names.add("김구라");
    	names.add("해골");
    	names.add("원숭이");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>친구 목록 입니다.</h3>
	<ul>
		<%for(int i=0; i<names.size(); i++){%>
			<li><%names.get(i);%></li>
		<%}%>
	</ul>
		<ul>
		<%for(String tmp:names){%>
			<li><%=tmp%></li>
		<%}%>
	</ul>
</body>
</html>