<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	MemberDto d1 = new MemberDto(1,"김구라","노량진");
	MemberDto d2 = new MemberDto(2,"해골","행신동");
	MemberDto d3 = new MemberDto(3,"원숭이","동물원");

	List<MemberDto> list = new ArrayList<>();
	list.add(d1);
	list.add(d2);
	list.add(d3);
	//위의 데이터는 DB에서 읽어온 sample 데이터라고 가정
	//EL 로 사용할 수 있도록 request 영역에 담기
	request.setAttribute("list", list);
%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_jstl/test03.jsp</title>
</head>
<body>
	<h1>회원목록</h1>
	<table>
		<thead>
			<tr>
				<td>번호</td>			
				<td>이름</td>			
				<td>주소</td>			
			</tr>
		</thead>
		<tbody>
		<%-- tmp = MemberDto 객체 / list = List<MemberDto> 객체 --%>
			<c:forEach var="tmp" items="${list }">
				<tr>
					<td>${tmp.num }</td>
					<td>${tmp.name }</td>
					<td>${tmp.addr }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>















