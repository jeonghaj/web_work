<%@page import="test.friend.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	MemberDao dao = new MemberDao();
	dao.delete(num);
	
	String cPath = request.getContextPath();
	response.sendRedirect(cPath+"/friend/list.jsp");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>