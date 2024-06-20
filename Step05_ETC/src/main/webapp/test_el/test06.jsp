<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//클라이언트에게 쿠키를 응답하기 위해서 Cookie 객체를 생성한다.
	//new Cookie( key, value) 
	Cookie cook=new Cookie("savedId", "kimgura");
	//쿠키 유지시간(초단위)
	cook.setMaxAge(60);
	//HttpServletResponse 객체에 addCookie() 메소드를 호출하면서 Cookie 객체를 전달하면 
	//클라이언트에게 응답할때 자동으로 쿠키도 응답된다. 
	response.addCookie(cook);
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_el/test06.jsp</title>
</head>
<body>
	<h1>쿠키에 저장된 내용도 EL 로 추출할수 있다.</h1>
	<p>savedId 라는 키값으로 60초 동안 유지되는 쿠키를 응답했습니다.</p>
	<a href="test07.jsp">쿠키값 확인하기</a>
</body>
</html>







