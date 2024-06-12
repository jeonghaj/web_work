<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3>회원 추가 양식</h3>
		<form class="row g-3" action="${pageContext.request.contextPath}/member/insert.jsp" method="post">
		<div class="col-md-6">
			<label class="form-label" for="name">이름</label>
			<input class="form-control" type="text" name="name" placeholder="이름 입력..." >
		</div>
		<div class="col-12">	
			<label class="form-label" for="addr">주소</label>
			<input class="form-control" type="text" name="addr" placeholder="주소 입력..." >
		</div>
		 <div class="col-12">
   			 <button type="submit" class="btn btn-primary">추가</button>
 		 </div>	
		</form>
	</div>
</body>
</html>