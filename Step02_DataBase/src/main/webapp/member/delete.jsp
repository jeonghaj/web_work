<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%
    	//1.  GET 방식 파라미터로 전달되는 삭제할 회원의 번호를 얻어온다.
    	int num = Integer.parseInt(request.getParameter("num"));
    	//2. MemberDao 객체를 이용해서 실제 DB에서 삭제
		new MemberDao().delete(num);
    	//3. 응답하기
    	//list.jsp => delete.jsp => list.jsp 이런 이동이기 떄문에 마치 새로고침 하는듯한 느낌을 줄 수 있다.
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