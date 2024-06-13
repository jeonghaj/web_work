<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container mt-5">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/">home</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/guest/list.jsp">방명록 목록</a></li>
				<li class="breadcrumb-item active">글작성</li>
			</ol>
		</nav>
		<h1 class="mb-4">좋은 글을 남겨주세요</h1>
		<form action="insert.jsp" method="post">
			<div class="form-floating mb-3">
				<input type="text" class="form-control" placeholder="작성자명" id="writer" name="writer" />
				<label for="writer" class="form-label">작성자</label>
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" placeholder="내용작성" style="height:200px"name="content" id="content" cols="30" rows="10"></textarea>
				<label for="content" class="form-label">내용</label>
			</div>
			<div class="form-floating mb-3">
				<input type="password" class="form-control" placeholder="비밀번호입력" id="pwd" name="pwd" />
				<label for="pwd" class="form-label">비밀번호</label>
			</div>
			<button type="submit" class="btn btn-primary">저장
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-save-fill" viewBox="0 0 16 16">
  				<path d="M8.5 1.5A1.5 1.5 0 0 1 10 0h4a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h6c-.314.418-.5.937-.5 1.5v7.793L4.854 6.646a.5.5 0 1 0-.708.708l3.5 3.5a.5.5 0 0 0 .708 0l3.5-3.5a.5.5 0 0 0-.708-.708L8.5 9.293z"/>
			</svg>
			</button>
		</form>
	</div>
</body>
</html>