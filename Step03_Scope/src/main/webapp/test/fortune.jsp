<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request 영역에 "fortuneToday" 라는 키값으로 저장된 String type 얻어내기
	String fortuneToday = (String)request.getAttribute("fortuneToday");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/fortune.jsp</title>
</head>
<body>
	<div class="container">
		<h3>오늘의 운세 페이지</h3>
		<p>오늘의 운세 : <strong><%=fortuneToday %></strong></p>
		<%-- EL 을 이용하면 request 영역에 담긴 데이터를 key 값으로 바로 추출할 수 있다 --%>
		<p>오늘의 운세  : <strong>${requestScope.fortuneToday }</strong></p>
		<p>오늘의 운세 : <strong>${fortuneToday }</strong></p>
	</div>
</body>
</html>