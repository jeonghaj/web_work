<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    	MemberDao dao = new MemberDao();
    	List<MemberDto> list = dao.getList();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>회원 목록 입니다.</h3>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>주소</th>
		</tr>
		<%for(MemberDto tmp:list){ %>
		<tr>
			<td><%=tmp.getNum() %></td>
			<td><%=tmp.getName() %></td>
			<td><%=tmp.getAddr() %></td>
		</tr>
		<%} %>
	</table>

</body>
</html>