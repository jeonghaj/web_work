<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
	List<MemberDto> members = new ArrayList<>();
	members.add(new MemberDto(1,"김구라","노량진"));
	members.add(new MemberDto(2,"해골","행신동"));
	members.add(new MemberDto(3,"원숭이","동물원"));
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>번호</th>	
		<th>이름</th>	
		<th>주소</th>	
		</tr>
		<%for(MemberDto dto:members) {%>
		<tr>
		<td><%=dto.getNum()%></td>
		<td><%=dto.getName()%></td>
		<td><%=dto.getAddr()%></td>
		</tr>
		<%} %>
</table>


</body>
</html>