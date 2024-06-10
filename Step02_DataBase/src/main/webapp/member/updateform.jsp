<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	MemberDao dao = new MemberDao();
    	List<MemberDto> list = dao.getList();
    	int num = Integer.parseInt(request.getParameter("num"));
    	int num2 = num-1;
    	MemberDto dto1 = list.get(num2);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			<h3>회원 정보 수정 양식</h3>
		<form action="${pageContext.request.contextPath}/member/update.jsp" method="post">
			<label><%=num %></label>
			<input type="hidden" value="<%=num %>" name="num">
			<input type="text" value="<%=dto1.getName() %>" name="name" >
			<input type="text" value="<%=dto1.getAddr() %>" name="addr" >
			<button type="submit" value="submit">수정</button>
		</form>
</body>
</html>