<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//1. 폼 전송되는 가입 회원 정보를 읽어와서
    	String id = request.getParameter("id");
    	String pwd = request.getParameter("pwd");
    	String email = request.getParameter("email");
    	//2. UserDto 객체에 담아서
    	UserDto dto = new UserDto();
    	dto.setId(id);
    	dto.setPwd(pwd);
    	dto.setEmail(email);
    	//3. UserDao 객체를 이용해서 DB에 저장한다.
    	boolean isSuccess = UserDao.getInstance().insert(dto);
    	//4. 응답하기
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<%if(isSuccess){ %>
			<p>
				<strong><%=id %></strong>님 가입 되었습니다.
				<a href="loginform.jsp">로그인 하러 가기</a>
			</p>
		<%}else{ %>
			<p>
				가입이 실패 했습니다.
				<a href="signup_form.jsp">다시 가입하러 가기</a>
			</p>
		<%} %>
	</div>
</body>
</html>