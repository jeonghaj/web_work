<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//session scopre 에서 수정할 회원의 아이디를 읽어옹ㄴ다.
    	String id = (String)session.getAttribute("id");
    	//폼 전송되는 구 비밀번호, 새 비밀번호 읽어오기
    	String pwd = request.getParameter("pwd");
    	String newPwd = request.getParameter("newPwd");
    	
    	//성공여부
    	boolean isSuccess=false;
    	//현재 비밀번호
    	String currentPwd = UserDao.getInstance().getData(id).getPwd();
    	//만일 현재 비밀번호하고 입력한 비밀번호와 같으면
    	if(currentPwd.equals(pwd)){
    		//수정 작업을 진행
    		UserDto dto = new UserDto();
    		dto.setId(id);
    		dto.setPwd(newPwd);
    		isSuccess = UserDao.getInstance().updatePwd(dto);
    	}
    	//만일 비밀번호 수정 성공이면 
    	if(isSuccess){
    		//로그아웃 처리
    		session.invalidate();
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
	<%if(isSuccess){ %>
		<p>
			비밀 번호를 수정하고 로그 아웃되었습니다.
			<a href="${pageContext.request.contextPath }/user/loginform.jsp">다시 로그인</a>
		</p>
	<%}else{ %>
		<p>
			구 비밀번호가 일치하지 않습니다.
			<a href="${pageContext.request.contextPath }/user/private/pwd_updateform.jsp">다시 시도</a>
		</p>
	<%} %>
</div>
</body>
</html>