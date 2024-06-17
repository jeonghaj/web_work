<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" id="msg" placeholder="메세지 입력..."/>
	<button id="sendBtn">전송</button>
	<button id="sendBtn2">전송2</button>
	<button id="sendBtn3">전송3</button>
	<script>
		document.querySelector("#sendBtn").addEventListener("click",()=>{
			//입력한 문자열 읽어오기
			const msg = document.querySelector("#msg").value;
			//fetch 함수를 호출하면서 GET 방식 파라미터로 send.jsp 페이지를 요청하면서 전달한다.
			fetch("send.jsp?msg="+msg)
			.then(res=>res.text())
			.then((data)=>{
				console.log(data)
			});
		});
		document.querySelector("#sendBtn2").addEventListener("click", ()=>{
			//입력한 문자열 읽어오기
			const msg=document.querySelector("#msg").value;
			//fetch 함수를 호출하면서 GET 방식 파라미터로 send.jsp 페이지를 요청하면서 전달한다.
			fetch("send2.jsp?msg="+msg)
			.then(res=>res.text())
			.then((data)=>{
				console.log(data);
				//data 는 json 형식의 문자열이기 때문에 제대로 활용하려면 object 로 바꿔야 한다.
				const result = JSON.parse(data);
				//result 는 object 이다 

			});
		});
	
		document.querySelector("#sendBtn3").addEventListener("click", ()=>{
			//입력한 문자열 읽어오기
			const msg=document.querySelector("#msg").value;
			//fetch 함수를 호출하면서 GET 방식 파라미터로 send.jsp 페이지를 요청하면서 전달한다.
			fetch("send2.jsp?msg="+msg)
			.then(res=>res.json())
			.then((data)=>{
				//data 는 응답된 json 문자열이 object 로 바뀐 data 이다. 
				console.log(data);
			});
		});
	</script>

</body>
</html>