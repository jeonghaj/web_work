<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button id="getBtn">회원정보 가져오기</button>
	<p>번호 : <strong id="num"></strong></p>
	<p>이름 : <strong id="name"></strong></p>
	<p>주소 : <strong id="addr"></strong></p>
	<button id="getBtn2">친구목록 가져오기</button>
	<button id="getBtn3">친구목록 가져오기2</button>
	<ul id="friend">
	
	</ul>
	<script>
	/*
		회원 정보를 가져오기 버턴을 눌렀을 떄 member.jsp 페이지로 fetch()요청을 하고 
		응답되는 데이터를 이용해서 위의 회원의 번호, 이름, 주소를 출력해 보세요.
	*/
	document.querySelector("#getBtn").addEventListener("click",()=>{
		fetch("${pageContext.request.contextPath }/member.jsp")
		.then(res=>res.json())
		.then((data)=>{
			document.querySelector("#num").innerText = data.num;
			document.querySelector("#name").innerText = data.name;
			document.querySelector("#addr").innerText = data.addr;
		});
	});
	/*
		친구 목록 가져오기 버튼을 눌렀을 때 friend.jsp 페이지로 fetch() 요청을 하고
		응답되는 데이터를 이용해서 친구이름을 ul에 li 요소를 이용해서 먹럭 출력해 보세요.
	*/
	document.querySelector("#getBtn2").addEventListener("click",()=>{
		fetch("${pageContext.request.contextPath }/friend.jsp")
		.then(res=>res.json())
		.then((data)=>{
			console.log(data);
			data.forEach((item)=>{
				const li = document.createElement("li");
				li.innerText = item;
				document.querySelector("#friend").append(li);
			})
		})
	})
	
	let myName = "김구라"
	let result = `내이름:\${myName}`;
		document.querySelector("#getBtn3").addEventListener("click",()=>{
		fetch("${pageContext.request.contextPath }/friend.jsp")
		.then(res=>res.json())
		.then((data)=>{
			data.forEach((item)=>{
			const li = `<li>\${item}</li>`
			document.querySelector("#friend").insertAdjacentHTML("beforeend",li);	
			})
		})
	})

	</script>
</body>
</html>