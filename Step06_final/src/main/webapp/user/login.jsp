<%@page import="java.net.URLEncoder"%>
<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//1. 폼전송되는 id, pwd 를 읽어와서
    	String id = request.getParameter("id");
    	String pwd = request.getParameter("pwd");
    	//2. id 에 해당하는 회원정보를 UserDao 객체를 이용해서 얻어온다.
    	UserDto dto = UserDao.getInstance().getData(id);
    	//3. 실제로 존재하는 아이디 이고 존재한다면 비밀번호도 일치하는지 비교해서
    	boolean isLoginSuccess = false;
    	if(dto!=null){
    		if(dto.getPwd().equals(pwd)){
    			//로그인 처리 해주기
    			session.setAttribute("id", id);
    			isLoginSuccess = true;
    		}
    	}
    //로그인 후 가야할 목적지 정보
    String url=request.getParameter("url");
    //로그인 실패를 대비해서 목적지 정보를 인코딩 한 결과도 준비한다.
    String encodedUrl = URLEncoder.encode(url);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%if(isLoginSuccess){ %>
			<p>
				<strong><%=dto.getId() %></strong>님 로그인 되었습니다.
				<a href="<%=url %>">확인</a>
			</p>
		<%}else{ %>
			<p>
				아이디 혹은 비밀번호가 틀려요
				<a href="${pageContext.request.contextPath }/user/loginform.jsp?url=<%=encodedUrl%>">다시 로그인</a>
			</p>
		<%} %>
	</div>
</body>
</html>