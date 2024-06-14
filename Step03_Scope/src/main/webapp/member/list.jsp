<%@page import="java.util.ArrayList"%>
<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//"list" 라는 키값으로 담긴 회원목록(List<MemberDto>)를 request 객체로 부터 얻어내기
List<MemberDto> list = (List<MemberDto>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원 목록 입니다.</h3>
	<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>주소</th>
		</tr>
	</thead>
	<tbody>	
		<%for (MemberDto tmp : list) {%>
		<tr>
			<td><%=tmp.getNum()%></td>
			<td><%=tmp.getName()%></td>
			<td><%=tmp.getAddr()%></td>
		</tr>
	</tbody>	
		<%}%>
	</table>
</body>
</html>