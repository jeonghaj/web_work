<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%
	//msg ��� �Ķ���� ������ ���޵Ǵ� ���ڿ� �о����
	String msg = request.getParameter("msg");
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>send.jsp</title>
</head>
<body>
 	<p>Ŭ���̾�Ʈ�� �޼��� �� �޾Ҿ�</p>
 	<p>�޼��� ���� : <strong><% out.print(msg); %></strong></p>
	<p>�޼��� ���� : <strong><%=msg %></strong></p>
</body>
</html>