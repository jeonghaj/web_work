<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	// 1. 폼 전송되는 이름과 주소를 추출한다.
    	String name = request.getParameter("name");	
    	String addr = request.getParameter("addr");
    	// 2. DB에 저장한다. // dao에 메소드 추가
    	MemberDao dao = new MemberDao();
    	MemberDto dto = new MemberDto();
    	dto.setName(name);
    	dto.setAddr(addr);
    	boolean result = dao.insert(dto);
    	// 3. 응답한다.

    	if(result){
    		System.out.println("성공적으로 추가하였습니다");
    	}
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h3>알람</h3>
		<%if(result){%>
			<p>
				<strong><%=name %></strong> 님의 정보를 저장했습니다.
				<a href="${pageContext.request.contextPath }/member/list.jsp">목록보기</a>
			</p>
		<% }else {%>
			<p>
				회원 정보 저장 실패!
				<a href="${pageContext.request.contextPath }/member/list.jsp">다시작성</a>
			</p>
		<%}%>
</div>
</body>
</html>