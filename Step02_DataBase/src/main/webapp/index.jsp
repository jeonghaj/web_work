<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>�ε��� ������ �Դϴ�</h1>
		<p>���ؽ�Ʈ ���(������Ʈ��) : <strong>${pageContext.request.contextPath }</strong></p>
		<ul>
			<li><a href="${pageContext.request.contextPath }/test.jsp">Connection ��ü ������ �׽�Ʈ</a></li>
			<li><a href="${pageContext.request.contextPath }/member/list.jsp">ȸ����� ����</a></li>
			<li><a href="${pageContext.request.contextPath }/friend/list.jsp">ģ����� ����</a></li>
			<li><a href="${pageContext.request.contextPath }/guest/list.jsp">����</a></li>
			
		</ul>
	</div>
</body>
</html>