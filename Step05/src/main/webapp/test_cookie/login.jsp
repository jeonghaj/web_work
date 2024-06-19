<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//가상의 로그인 페이지
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	//체크박스를 체크한 상태로 로그인 버튼을 누르면 null 이 아니다. (체크하지 않으면 null)
	String isSave=request.getParameter("isSave");
	if(isSave != null){
		//아이디 비밀번호를 쿠키로 응답하고 1주일 동안 유지되도록 한다.
		Cookie cook1=new Cookie("savedId", id);
		Cookie cook2=new Cookie("savedPwd", pwd);
		cook1.setMaxAge(60*60*24*7);
		cook2.setMaxAge(60*60*24*7);
		response.addCookie(cook1);
		response.addCookie(cook2);
	}else{
		//특정 키값으로 저장된 쿠키값 삭제하기 (value 에는 아무 값이나 넣어도 상관없다)
		Cookie cook1=new Cookie("savedId", "");
		Cookie cook2=new Cookie("savedPwd", null);
		cook1.setMaxAge(0);
		cook2.setMaxAge(0);
		response.addCookie(cook1);
		response.addCookie(cook2);
	}	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_cookie/login.jsp</title>
</head>
<body>
	<p><%=id %> 님 로그인 되었습니다.</p>
	<a href="login_form.jsp">다시 테스트</a>
</body>
</html>










