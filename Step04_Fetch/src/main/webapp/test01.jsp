<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>fetch 함수 테스트</h3>
	<button id="myBtn">눌러보셈</button>
	
	<a href="${pageContext.request.contextPath }/index.jsp">링크</a>
	
	<script>
	//페이지 로딩 시점에 버튼을 눌렀을 때 실행할 함수가 등록된다.
		document.querySelector("#myBtn").addEventListener("click",()=>{
			//버튼을 누르면 index.jsp 페이지로 요청을 한다.
			fetch("${pageContext.request.contextPath }/index.jsp")
			//요청에 대한 응답을 받으면 아래가 실행된다.
			.then((res)=>{
				/*
					서버(jsp or servlet) 에서 응답한 문자열이 json 형식이면
					return res.json();
					
					그 외의 형식이면 html, xml, plain text 등등
					return res.text();
				*/
				return res.text();
			})
			//위의 함수가 리턴되면 연이어서 아래가 실행된다.
			.then((data)=>{
				/*
					위의 them() 함수에서 res.json() 를 리턴하면 data 는 응답된 json 문자열을
					JSON.parse() 과정을 이미 거친 object 나 array 이다.
					
					위의 then() 함수에서 res.text() 를 리턴하면 data 는 서버가 응답한 문자열(string) 이다.
				*/
				console.log(data);
			});
		})
	</script>
</body>
</html>