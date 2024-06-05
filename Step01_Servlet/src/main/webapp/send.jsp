<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%
	//msg 라는 파라미터 명으로 전달되는 문자열 읽어오기
	String msg = request.getParameter("msg");
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>send.jsp</title>
</head>
<body>
 	<p>클라이언트야 메세지 잘 받았어</p>
 	<p>메세지 내용 : <strong><% out.print(msg); %></strong></p>
	<p>메세지 내용 : <strong><%=msg %></strong></p>
</body>
</html>