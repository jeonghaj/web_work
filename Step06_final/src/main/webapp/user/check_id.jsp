<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//1.GET 방식 파라미터로 전달되는입력한 id 값을 읽어온다.
    	String id = request.getParameter("id");
    	//2.DB 에서 해당 회원 정보가 있는지 select 해본다.
    	UserDto dto = UserDao.getInstance().getData(id);
    	//3.해당 아이디 사용 가능 여부를 json 문자열로 응답한다.
    	//boolean canUse = dto == null? true : false ;
    	boolean canUse = false;
    	if(dto == null){
    		canUse = true;
    	}    
    %>
{"canUse":true}