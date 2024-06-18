<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //get 방식 parameter 로 전송되는 입력한 닉네일을 읽어온다.
    String nick = request.getParameter("nick");
    
    //DB 에 존재하는지 확인해서  사용가능한 닉네임인지 여부를 json 문자열로 응답해야한다.
    boolean canUse=true;
    if(nick.equals("gura")){
    	canUse = false;
    }
    
    %>
{"canUse":<%=canUse %>}