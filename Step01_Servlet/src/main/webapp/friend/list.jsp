<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    	List<String> names = new ArrayList<>();
    	names.add("�豸��");
    	names.add("�ذ�");
    	names.add("������");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>ģ�� ��� �Դϴ�.</h3>
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