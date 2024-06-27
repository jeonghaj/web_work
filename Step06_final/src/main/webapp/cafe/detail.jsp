<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//자세히 보여줄 글의 번호를 읽어온다.
	int num = Integer.parseInt(request.getParameter("num"));
	//DB 에서 해당 글의 정보를 얻어와서
	CafeDto dto = CafeDao.getInstance().getData(num);
	
	//응답한다.
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3>글 상세 보기</h3>
		<table>
			<tr>
				<th>글번호</th>
				<td><%=dto.getNum() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=dto.getWriter() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=dto.getTitle() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=dto.getViewCount() %></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><%=dto.getRegdate() %></td>
			</tr>
			<tr>
				<td colspan="2">
					<div><%=dto.getContent() %></div>
				</td>
			</tr>
		</table>
		<%
			//로그인된 아이디가 있으면 읽어온다(null 일수도 있다)
			String id=(String)session.getAttribute("id");
		%>
		<%-- 만일 글 작성자가 로그인된 아이디와 같다면 수정, 삭제 링크를 제공한다. --%>
		<%if(dto.getWriter().equals(id)){ %>
			<a class="btn btn-outline-success btn-sm" href="private/updateform.jsp?num=<%=dto.getNum()%>">수정</a>
			<a class="btn btn-outline-danger btn-sm" href="javascript:" onclick="deleteConfirm()">삭제</a>
			<script>
				function deleteConfirm(){
					const isDelete=confirm("이 글을 삭제 하겠습니까?");
					if(isDelete){
						//javascript 를 이용해서 페이지 이동 시키기
						location.href="private/delete.jsp?num=<%=dto.getNum()%>";
					}
				}
			</script>
		<%} %>				
	</div>
</body>
</html>