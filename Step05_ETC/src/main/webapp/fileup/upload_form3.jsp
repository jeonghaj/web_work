<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/upload_form3.jsp</title>
</head>
<body>
	<div class="container">
		<h3>이미지 업로드 폼</h3>
		<form action="upload3.jsp" method="post" enctype="multipart/form-data" id="myForm">
			<input type="text" name="title" placeholder="설명 입력"/><br />
			이미지 <input type="file" name="myImage" accept="image/*"/><br />
			<button type="submit">업로드</button>
		</form>
		<img id="image" width="300"/>
	</div>
	<script>
		document.querySelector("#myForm").addEventListener("submit", (e)=>{
			//폼 전송 막기
			e.preventDefault();
			//폼에 입력하거나 선택된 데이터를 FormData 객체에 담는다.
			const data=new FormData(e.target);
			//fetch() 함수를 이용해서 페이지 전환없이 전송한다.
			fetch("upload3.jsp", {
				method:"post",
				body:data
			})
			.then(res=>res.json())
			.then(data=>{
				//data 는 {saveFileName:"업로드된 이미지의 파일명"} 형태의 object 이다.
				console.log(data);
				//이미지를 로딩할 경로 구성하기 
				const src="${pageContext.request.contextPath}/upload/"+data.saveFileName;
				//이미지 로딩하기
				document.querySelector("#image").setAttribute("src", src);
			});
			
		});
	</script>
</body>
</html>











