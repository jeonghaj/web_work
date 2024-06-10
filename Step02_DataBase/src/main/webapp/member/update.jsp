<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String name = request.getParameter("name");
    	String addr = request.getParameter("addr");
    	int num = Integer.parseInt(request.getParameter("num"));
    
    	MemberDao dao = new MemberDao();
    	MemberDto dto = new MemberDto();
    	dto.setName(name);
    	dto.setAddr(addr);
    	dto.setNum(num);
    	dao.update(dto);
    	
    	String cPath=request.getContextPath();
    	//특정 경로로 요청을 다시 하라는 리다이렉트 응답하기
    	response.sendRedirect(cPath+"/member/list.jsp");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>