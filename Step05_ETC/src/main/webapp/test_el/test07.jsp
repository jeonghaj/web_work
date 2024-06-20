<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cooks=request.getCookies();
	String savedId="";
	for(Cookie tmp : cooks){
		if(tmp.getName().equals("savedId")){
			savedId=tmp.getValue();
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_el/test07.jsp</title>
</head>
<body>
	<p>savedId 라는 키값으로 저장된 쿠키값 : <strong><%=savedId %></strong></p>
	<p>savedId 라는 키값으로 저장된 쿠키값 : <strong>${cookie.savedId.value }</strong></p>
</body>
</html>