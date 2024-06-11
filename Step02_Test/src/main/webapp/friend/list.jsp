<%@page import="test.friend.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.friend.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%
    List<MemberDto> list = new MemberDao().getList();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="insertform.jsp">친구 추가</a>
<h3>친구 목록 입니다</h3>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>전화번호</th>
		</tr>
		<%for(MemberDto tmp:list){ %>
		<tr>
			<td><%=tmp.getNum() %></td>
			<td><%=tmp.getName() %></td>
			<td><%=tmp.getPnum() %></td>
			<td><a href="${pageContext.request.contextPath }/friend/delete.jsp?num=<%=tmp.getNum()%>">삭제</a></td>
			<td><a href="${pageContext.request.contextPath }/friend/updateform.jsp?num=<%=tmp.getNum()%>">수정</a></td>
		</tr>
		<%} %>
	</table>
</body>
</html>