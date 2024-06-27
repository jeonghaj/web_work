<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<p>
			<strong>${sessionScope.id }</strong> 님!
			<strong>${param.id }</strong> 번 상품
			<strong>${param.count }</strong> 개가 정상적으로 주문 되었습니다!
		</p>
		<p>
			<a href="${pageContext.request.contextPath }/index.jsp">index</a>
		</p>
	</div>
</body>
</html>