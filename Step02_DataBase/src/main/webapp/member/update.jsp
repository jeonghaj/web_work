<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	// 폼 전송되는 수정할 회원의 번호, 이름, 주소를 추출한다.
    	String name = request.getParameter("name");
    	String addr = request.getParameter("addr");
    	int num = Integer.parseInt(request.getParameter("num"));
    	// DB에 수정 반영한다.
    	MemberDto dto = new MemberDto(num, name, addr);
    	//new MemberDao().update(dto);
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
		alert("수정했습니다.")
		location.href="${pageContext.request.contextPath }/member/list.jsp"
	<%}else{%>
		alert("수정 실패")
		location.href="${pageContext.request.contextPath }/member/updateform.jsp?num=<%=num%>"
	<%}%>
</script>

</body>
</html>