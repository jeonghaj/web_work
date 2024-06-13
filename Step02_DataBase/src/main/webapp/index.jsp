<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>index.jsp</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>
	<%-- /include/navbar.jsp ���������� �̺κ��� �����ϵ��� �Ѵ�. 
		<jsp:include page="webapp �������� jsp ������ ���">
		
		- include �� ��ų�� include �� jsp �������� �Ķ���͸� �����Ҽ��� �ִ�.
		<jsp:param value="������ ���ڿ�" name="�Ķ���͸�"/>
		- ���޵� �Ķ���ʹ� HttpServletRequest ��ü�� �̿��ؼ� include �� ���������� ������ �� �ִ�.
		- String result = request.getParameter("current"); // "index"
	--%>

	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="index" name="current" />
	</jsp:include>
	<div class="container">
		<h1>�ε��� ������ �Դϴ�</h1>
		<p>
			���ؽ�Ʈ ���(������Ʈ��) : <strong>${pageContext.request.contextPath }</strong>
		</p>
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath }/test.jsp">Connection
					��ü ������ �׽�Ʈ</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath }/member/list.jsp">ȸ�����
					����</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath }/friend/list.jsp">ģ�����
					����</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath }/guest/list.jsp">����</a></li>

		</ul>
		<div id="carouselExampleIndicators" class="carousel slide">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="images/top01.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/top02.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/top03.jpg" class="d-block w-100" alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>

	</div>
	<%--/include/footer.jsp ���� footer �����ϰ� �ϱ� --%>
	<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>