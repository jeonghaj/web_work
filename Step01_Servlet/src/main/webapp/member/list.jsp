<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
	List<MemberDto> members = new ArrayList<>();
	members.add(new MemberDto(1,"�豸��","�뷮��"));
	members.add(new MemberDto(2,"�ذ�","��ŵ�"));
	members.add(new MemberDto(3,"������","������"));
    
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
		<th>��ȣ</th>	
		<th>�̸�</th>	
		<th>�ּ�</th>	
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