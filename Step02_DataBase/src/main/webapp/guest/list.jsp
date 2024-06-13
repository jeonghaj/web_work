<%@page import="test.guest.dto.GuestDto"%>
<%@page import="java.util.List"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	GuestDao dao = GuestDao.getInstance();
    	List<GuestDto> list = dao.getList();
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/include/navbar.jsp">
	<jsp:param value="guest" name="current"/>
</jsp:include>
	<div class="container">
			<a href="${pageContext.request.contextPath }/guest/insertform.jsp">
			<span class="visually-hidden">새글작성</span>
				<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-file-earmark-plus-fill" viewBox="0 0 16 16">
 		 			<path d="M9.293 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4.707A1 1 0 0 0 13.707 4L10 .293A1 1 0 0 0 9.293 0M9.5 3.5v-2l3 3h-2a1 1 0 0 1-1-1M8.5 7v1.5H10a.5.5 0 0 1 0 1H8.5V11a.5.5 0 0 1-1 0V9.5H6a.5.5 0 0 1 0-1h1.5V7a.5.5 0 0 1 1 0"/>
				</svg>
			</a>	
		<h1 class="mb-4">방명록 목록입니다</h1>
		<div class="mb-3" >
		</div>
		<table class="table table-striped table-bordered">
			<colgroup>
			<col class="col-1"/>
			<col class="col-1"/>
			<col class="col-5"/>
			<col class="col-2"/>
			<col class="col-1"/>
			<col class="col-2"/>		
		</colgroup>
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>등록일</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%for(GuestDto tmp:list){ %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getWriter() %></td>
						<td><textarea class="form-contral" rows="2"><%=tmp.getContent() %></textarea></td>
						<td><%=tmp.getRegdate() %></td>
						<td><a href="updateform.jsp?num=<%=tmp.getNum() %>" class="btn btn-primary">수정</a></td>
						<td>
							<form action="delete.jsp" method="post">
								<!-- 화면상에 보이지는 않지만 폼 전송될때 같이 전송되는 input -->
								<input type="hidden" name="num" value="<%=tmp.getNum() %>" />
								<div class="input-group">
									<input type="text" class="form-control" name="pwd" />
									<button type="submit"  class="btn btn-danger btn-sm">삭제</button>
								</div>
							</form>
						</td>
					</tr>
				<%} %>
			
			</tbody>
		</table>
	</div>

</body>
</html>