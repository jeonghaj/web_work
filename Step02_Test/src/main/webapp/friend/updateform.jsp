<%@page import="test.friend.dto.MemberDto"%>
<%@page import="test.friend.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int num = Integer.parseInt(request.getParameter("num"));
    	
    MemberDto dto = new MemberDao().getData(num);
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/friend/update.jsp" method="post">
	<div>
	<label for="num">번호</label>
	<input type="text" name="num" id="num" value="<%=dto.getNum() %>" readonly/>	
	</div>
	<div>
	<label for="name">이름</label>
	<input type="text" name="name" id="name" value="<%=dto.getName()%>"/>	
	</div>
	<div>
	<label for="pnum">전화번호</label>
	<input type="text" name="pnum" id="pnum" value="<%=dto.getPnum()%>"/>	
	</div>
	<button type="submit">수정</button>
	<button type="reset">리셋</button>
	
	</form>
</body>
</html>