<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼 전송되는 "nick" 을 읽어온다.
	String nick = request.getParameter("nick");
	//session 영역에 "nick" 이라는 키값으로 저장한다.
	session.setAttribute("nick", nick);
	//세션 유지시간 초단위로 설정하기
	session.setMaxInactiveInterval(30);
	//응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test/seve.jsp</title>
</head>
<body>
	<p><strong><%=nick %></strong>이라는 닉네임을 기억 하겠습니다.</p>
	<p> 30분 동안 아무런 요청을 하지 않거나 웹브라우저를 닫으면 자동 삭제 됩니다.</p>
	<a href="../index.jsp">인덱스로 이동하기</a>
</body>
</html>