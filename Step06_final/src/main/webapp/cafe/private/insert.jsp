<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//폼 전송되는 title, content 읽어내기
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	//글 작성자 얻어내기
	String writer=(String)session.getAttribute("id");
	//글 정보를 DB에 저장하기
	CafeDto dto = new CafeDto();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setWriter(writer);
	
	boolean isSuccess = CafeDao.getInstance().insert(dto);
	//응답하기
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="card w-50 mt-5">
			<%if(isSuccess){ %>
				<div class="card-body">
					<h5 class="card-title">알림</h5>
					<p class="card-text"><%=writer %>  님이 작성한 새글을 저장했습니다</p>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/cafe/list.jsp">확인</a>
				</div>
			<%}else{ %>
				<div class="card-body">
					<h5 class="card-title">알림</h5>
					<p class="card-text text-danger">글 저장 실패!</p>
					<a class="btn btn-warning" href="${pageContext.request.contextPath}/cafe/private/insertform.jsp">다시 작성하기</a>
				</div>
			<%} %>
		</div>
	</div>
</body>
</html>