<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//1. 수정할 글 번호를 읽어온다.
    	int num = Integer.parseInt(request.getParameter("num"));
    	//2. 글번호에 해당하는 글정보를 DB에서 얻어온다.
    	GuestDto dto = GuestDao.getInstance().getData(num);
    	//3. rmf 글 수정 방식을 응답한다.
    
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
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/">home</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/guest/list.jsp">방명록 목록</a></li>
				<li class="breadcrumb-item active">글수정</li>
			</ol>
		</nav>	
		<h1>방명록 글 수정 폼</h1>
		<form action="${pageContext.request.contextPath }/guest/update.jsp" method="post">
			<div class="mb-3">
				<label for="num" class="form-label">번호</label>
				<input type="text" id="num" name="num" class="form-control" value="<%=dto.getNum() %>" readonly/>
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">작성자</label>
				<input type="text" id="writer" name="writer" class="form-control" value="<%=dto.getWriter()%>"/>
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">내용</label>
				<textarea id="content" name="content" class="form-control" rows="5"><%=dto.getContent() %></textarea>
			</div>
			<div class="mb-3">
				<label for="pwd" class="form-label">글 작성시 입력했던 비밀번호</label>
				<input type="password" id="pwd" name="pwd" class="form-control"/>
			</div>
  			<button type="submit" class="btn btn-primary">수정확인</button>
            <button type="reset" class="btn btn-secondary">취소</button>
		</form>
	</div>
</body>
</html>