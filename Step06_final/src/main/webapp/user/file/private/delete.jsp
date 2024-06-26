<%@page import="java.io.File"%>
<%@page import="test.file.dao.FileDao"%>
<%@page import="test.file.dto.FileDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//삭제할 파일의 번호
	int num=Integer.parseInt(request.getParameter("num"));
	//삭제할 파일의 정보
	FileDto dto=FileDao.getInstance().getData(num);
	//로그인된 아이디와 글의 작성자가 일치하는지 확인해서 일치하지 않으면 에러 페이지를 응답한다 
	String id=(String)session.getAttribute("id");
	if(!dto.getWriter().equals(id)){
		//에러페이지를 응답하도록 한다.  .sendError(에러코드, 에러메세지) 
		response.sendError(HttpServletResponse.SC_FORBIDDEN , "남의 파일 지우면 혼난다!");
		return; //메소드를 여기서 끝내기 (service() 메소드)
	}
	//1. 파일 시스템에서 실제로 파일 삭제하기
	String path=application.getRealPath("/upload")+File.separator+dto.getSaveFileName();
	File f=new File(path);
	f.delete();
	//2. DB 에서 파일 정보 삭제하기
	FileDao.getInstance().delete(num);
	//3. 응답하기 
	String cPath=request.getContextPath();
	response.sendRedirect(cPath+"/file/list.jsp"); //파일 목록보기로 다시 리다일렉트 이동 시킨다.
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>