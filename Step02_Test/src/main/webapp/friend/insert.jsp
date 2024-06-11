<%@page import="test.friend.dao.MemberDao"%>
<%@page import="test.friend.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    MemberDto dto = new MemberDto();
   
    String name = request.getParameter("name");
    String pnum = request.getParameter("pnum");
    dto.setName(name);
    dto.setPnum(pnum);
    
    boolean isSuccess = new MemberDao().insert(dto);
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(isSuccess){ %>
	<p>친구 추가에 성공하였습니다.</p>
	<a href="${pageContext.request.contextPath }/friend/list.jsp">친구 목록으로 돌아가기</a>
<%}else{ %>
	<p>친구 추가에 실패하였습니다.</p>
	<a href="${pageContext.request.contextPath }/friend/list.jsp">친구 목록으로 돌아가기</a>
	<a href="${pageContext.request.contextPath }/friend/insertform.jsp">다시 시도하기</a>
<%} %>
</body>
</html>