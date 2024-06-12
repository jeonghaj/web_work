<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//폼 점송되는 삭제할 글의 글번호와 비밀번호를 추출
    	int num = Integer.parseInt(request.getParameter("num"));
    	String pwd = request.getParameter("pwd");
    	
    	GuestDao dao = GuestDao.getInstance();
    	//삭제할 글 정보를 얻어와서
    	GuestDto dto = dao.getData(num);
    	//비밀번호가 일치하는지 확인( java 에서는 equals 메소드 활용)
    	if(pwd.equals(dto.getPwd())){
    		//DB 에서 실제로 삭제하고
    		dao.delete(num);
    		// "/guest/list.jsp" 페이지로 리다일렉트 이동하라는 응답
    		String cPath = request.getContextPath();
    		response.sendRedirect(cPath+"/guest/list.jsp");
    	}
    	//삭제 실패임을 응답한다.
    	String cPath = request.getContextPath();
		response.sendRedirect(cPath+"/guest/list.jsp");

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
<script>
	alert("비밀번호가 일치하지 않습니다.")
</script>

</body>
</html>