<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath }/fortune">오늘의 운세</a></li>
			<li><a href="${pageContext.request.contextPath }/test/fortune.jsp">테스트</a></li>
			<li><a href="${pageContext.request.contextPath }/member">테스트</a></li>
			<li><a href="${pageContext.request.contextPath }/member/list">회원 목록 보기</a></li>
		</ul>
		
		<form action="${pageContext.request.contextPath }/test/save.jsp" method="post">
			<input type="text" name="nick" placeholder="닉네임 입력..." />
			<button type="submit">닉네임 기억 시키기</button>
		</form>
		
	
		<form action="${pageContext.request.contextPath }/test/save" method="post">
			<input type="text" name="nick" placeholder="닉네임 입력..." />
			<button type="submit">닉네임 기억 시키기</button>
		</form>		
		
			<%
			//session 영역에 "nick" 이라는 키값으로 저장된 문자열이 있는지 읽어와 본다.
			String nick = (String)session.getAttribute("nick");
		%>
		<%if(nick != null){ %>
			<p>
				<strong><%=nick %></strong>님 반갑습니다.
				<a href="${pageContext.request.contextPath }/test/logout.jsp">로그아웃(세션 초기화)</a>
			</p>
		<%} %>
		
	</div>
</body>
</html>