<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String msg = request.getParameter("msg");   
    %>
{"toClient":"클라이언트야 메세지 잘 받았어", "msg":"<%=msg %>"}