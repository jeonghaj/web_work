<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//1. 수정할 회원의 번호를 읽어온다.
    	int num = Integer.parseInt(request.getParameter("num"));
    	//2. DB에서 해당 회언의 정보를 얻어온다.
    	MemberDto dto = new MemberDao().getData(num);
    	//3. 회원 수정 양식을 응답한다.	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원 정보 수정 양식</h3>
	<form action="${pageContext.request.contextPath }/member/update.jsp" method="post">
		<div>
			<label for="num">번호</label>
			<input type="text" name="num" id="num" value="<%=dto.getNum() %>" readonly/>
		</div>
		<div>
			<label for="name">이름</label>
			<input type="text" name="name" id="name" value="<%=dto.getName()%>"/>
		</div>
		<div>
			<label for="addr">주소</label>
			<input type="text" name="addr" id="addr" value="<%=dto.getAddr()%>"/>
		</div>
		<button type="submit">수정 확인</button>
		<button type="reset">취소</button>
	
	
	</form>
</body>
</html>

