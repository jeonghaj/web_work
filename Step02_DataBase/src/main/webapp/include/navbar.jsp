<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	웹브라우저에 출력되지 않는 jsp 페이지에서의 주석 입니다.
 --%>
 <!-- 
 	웹브라우저에 출력되지만 웹브라우저가 무시하는 주석 
  -->
 <%
	//navbar.jsp 페이지가 어떤 페이지에 include 되었는지 정보 읽어오기
	String currentPage=request.getParameter("current"); // "index" or "member" or "guest" 
 %>
<nav class="navbar bg-primary navbar-expand-md" data-bs-theme="dark">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">Acorn</a>
		<button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target=".navbar-collapse">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link <%= currentPage.equals("member") ? "active" : "" %>" href="${pageContext.request.contextPath }/member/list.jsp">회원목록</a>
				</li>
				<li class="nav-item">
					<a class="nav-link <%= currentPage.equals("guest") ? "active" : "" %>" href="${pageContext.request.contextPath }/guest/list.jsp">방명록</a>
				</li>
			</ul>
		</div>
	</div>
</nav>    


