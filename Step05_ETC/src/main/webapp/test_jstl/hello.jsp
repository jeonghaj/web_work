<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
	/WEB-ING/lib/jstl-1.2.jar 파일에서 jsp/jstl/core 라이브러리를 import해서
	c 라는 접두어(prefix)로 사용하겠다는 의미
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_jstl/hello.jsp</title>
</head>
<body>
	<h1>Java Standard Tag Library</h1>
	<c:forEach var="i" begin="0" end="9">
		<p>안녕 JSTL <strong>${i}</strong></p>
	</c:forEach>
	
	<h1>순수 java code 를 이용해서 같은 동작</h1>
	<%for(int i=0;i<10;i++){ %>
		<p>안녕 JSTL <strong><%=i %></strong></p>
	<%} %>
</body>
</html>












