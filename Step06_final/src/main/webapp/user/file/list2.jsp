<%@page import="test.file.dao.FileDao"%>
<%@page import="test.file.dto.FileDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	
	//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	int pageNum=1;
	
	//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	String strPageNum=request.getParameter("pageNum");
	//만일 페이지 번호가 파라미터로 넘어 온다면
	if(strPageNum != null){
		//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
		pageNum=Integer.parseInt(strPageNum);
	}	
	
	//보여줄 페이지의 시작 ROWNUM
	int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	//보여줄 페이지의 끝 ROWNUM
	int endRowNum=pageNum*PAGE_ROW_COUNT;
	
	//하단 시작 페이지 번호 
	int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
	//하단 끝 페이지 번호
	int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	//전체 글의 갯수
	int totalRow=FileDao.getInstance().getCount();
	//전체 페이지의 갯수 구하기
	int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	if(endPageNum > totalPageCount){
		endPageNum=totalPageCount; //보정해 준다. 
	}	

	//파일 전체 목록 얻어오기 
	//List<FileDto> list=FileDao.getInstance().getList();
	
	//pageNum 에 해당하는 목록만 얻어오기
	List<FileDto> list=FileDao.getInstance().getList(startRowNum, endRowNum);
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
		<a href="${pageContext.request.contextPath }/user/file/private/upload_form.jsp">업로드 하러 가기</a>
		<h1>자료실 목록 입니다</h1>
		<table class="table table-stripped">
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>파일명</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<%for(FileDto tmp:list){%>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getWriter() %></td>
						<td><%=tmp.getTitle() %></td>
						<td>
						<a href="download.jsp?num=<%=tmp.getNum() %>"><%=tmp.getOrgFileName() %></a>
						</td>
						<td><%=tmp.getRegdate() %></td>
					</tr>
				<% }%>
			</tbody>
		</table>
		<!-- 페이징 UI -->
		<ul class="pagination">
			<%--
				startPageNum 이 1 이 아닌 경우에만 Prev 링크를 제공한다. 
			--%>
			<%if(startPageNum != 1){ %>
				<li class="page-item">
					<a class="page-link" href="list2.jsp?pageNum=<%=startPageNum-1 %>">Prev</a>
				</li>
			<%} %>
		
			<%for(int i=startPageNum; i<=endPageNum; i++){ %>
				<%if(i == pageNum){ %>
					<li class="page-item active">
						<a class="page-link" href="list2.jsp?pageNum=<%=i %>"><%=i %></a>
					</li>
				<%}else{ %>
					<li class="page-item">
						<a class="page-link" href="list2.jsp?pageNum=<%=i %>"><%=i %></a>
					</li>
				<%} %>	
			<%} %>
			
			<%--
				마지막 페이지 번호가 전체 페이지의 갯수보다 작으면 Next 링크를 제공한다. 
			--%>
			<%if(endPageNum < totalPageCount){ %>
				<li class="page-item">
					<a class="page-link" href="list2.jsp?pageNum=<%=endPageNum+1 %>">Next</a>
				</li>
			<%} %>
		</ul>		
	</div>
</body>
</html>
