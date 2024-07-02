<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. GET 방식 파라미터로 전달되는 삭제할 파일의 글번호 읽어오기
	int num=Integer.parseInt(request.getParameter("num"));
	//2. 로그인된 아이디와 글의 작성자가 일치하는지 확인해서 일치하지 않으면 에러 페이지를 응답한다 
	CafeDto dto=CafeDao.getInstance().getData(num); //글정보
	String id=(String)session.getAttribute("id"); //로그인된 사용자의 아이디
	if(!dto.getWriter().equals(id)){//만일 글 작성자가 로그인된 사용자와 다르면
		//에러페이지를 응답하도록 한다. 
		response.sendError(HttpServletResponse.SC_FORBIDDEN , "남의 파일 지우면 혼난다!");
		return; //메소드를 여기서 끝내기
	}
	//3. DB 에서 삭제하기
	CafeDao.getInstance().deleteRef(num);
	CafeDao.getInstance().delete(num);
	//4. 응답하기
	String cPath=request.getContextPath();
	response.sendRedirect(cPath+"/cafe/list.jsp");	
%>




