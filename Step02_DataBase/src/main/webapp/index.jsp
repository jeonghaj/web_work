<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>index.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar bg-primary navbar-expand-md" data-bs-theme="dark">
		<div class="container">
			<a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">Acorn</a>
			<button class="navbar-toggler" data-bs-target=".navbar-collpase">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath }/member/list.jsp">ȸ�����</a>
					</li>
					<li>
						<a class="nav-link" href="${pageContext.request.contextPath }/guest/list.jsp">����</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<h1>�ε��� ������ �Դϴ�</h1>
		<p>���ؽ�Ʈ ���(������Ʈ��) : <strong>${pageContext.request.contextPath }</strong></p>
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/test.jsp">Connection ��ü ������ �׽�Ʈ</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/member/list.jsp">ȸ����� ����</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/friend/list.jsp">ģ����� ����</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/guest/list.jsp">����</a></li>
			
		</ul>
	</div>
</body>
</html>