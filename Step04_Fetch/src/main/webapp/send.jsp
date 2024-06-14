<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//GET 방식 요청 파라미터 읽어오기
	String msg = request.getParameter("msg");
	System.out.println("msg:"+msg);

%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	메세지 잘 받았어 클라이언트야
</body>
</html>