<%@page import="test.friend.dao.MemberDao"%>
<%@page import="test.friend.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int num = Integer.parseInt(request.getParameter("num"));
    	String name = request.getParameter("name");
    	String pnum = request.getParameter("pnum");
    	
    	MemberDto dto = new MemberDto(num, name, pnum);
    	boolean isSuccess = new MemberDao().update(dto);
  
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
<%if(isSuccess){%>
	alert("목록을 수정했습니다.")
	location.href = "${pageContext.request.contextPath }/friend/list.jsp"
<%}else{%>
	alert("수정에 실패하였습니다.")
	location.href="${pageContext.request.contextPath }/friend/updateform.jsp?num=<%=num%>"
<%}%>

</script>

</body>
</html>