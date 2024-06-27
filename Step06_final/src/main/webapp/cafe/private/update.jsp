<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	int num = Integer.parseInt(request.getParameter("num"));
	CafeDto dto = new CafeDto();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setNum(num);
	
	CafeDao.getInstance().update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath }/cafe/list.jsp">list</a>
	</div>
</body>
</html>