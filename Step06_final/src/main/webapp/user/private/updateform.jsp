<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//session scope 에 저장된 아이디를 이용해서
    	String id = (String)session.getAttribute("id");
    	//수정할 회원의 정보를 얻어온다.
    	UserDto dto = UserDao.getInstance().getData(id); 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/updateform.jsp</title>
<style>
	#profileImage{
		width: 100px;
		height: 100px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
	#image{
		display: none;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>회원 정보 수정 양식</h3>
		<form action="update.jsp" method="post">
		<!-- 
			화면에 보이지는 않지만 폼을 제출할때 profile 이라는 파라미터 명으로
			upload 폴더에 저장된 파일명이 전송되도록 한다.
		 -->
			<input type="hidden" name="profile" value="<%=dto.getProfile()%>"/>
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" value="<%=dto.getId() %>" readonly/>
			</div>
			<div>
				<label for="email">이메일</label>
				<input type="text" id="email" name="email" value="<%=dto.getEmail()%>"/>
			</div>
			<div>
				<label>프로필 이미지</label>
				<div>
					<a href="javascript:" id="profileLink">
						<%if(dto.getProfile()==null){ %>
							<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
								<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
								<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
							</svg>
						<%}else{ %>
							<img id="profileImage" src="${pageContext.request.contextPath}/upload/<%=dto.getProfile() %>" alt="프로필 이미지" />
						<%} %>
					</a>
				</div>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
		<!-- 화면에 보이지 않는 이미지 선택할 input type="file" -->
		<input type="file" id="image"  accept="image/*"/>
	</div>
	<script>
		//링크를 클릭했을때 
		document.querySelector("#profileLink").addEventListener("click", ()=>{
			// input type="file" 요소를 강제 클릭해서 파일 선택 창을 띄운다.
			document.querySelector("#image").click();
		});
		//새로운 이미지가 선택되었을때
		document.querySelector("#image").addEventListener("change", (e)=>{
			//전송할 file 데이터를 FormData 객체에 담는다.
			const fileData=e.target.files[0];
			const data=new FormData();
			// myImage 라는 파라미터명으로 fileData 를 담는다. 
			data.append("myImage", fileData);
			//fetch() 함수를 이용해서 페이지 전환없이 업로드
			fetch("upload_profile.jsp",{
				method:"post",
				body:data
			})
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				//img 요소를 만들 문자열 구성
				let img=`<img id="profileImage" 
					src="${pageContext.request.contextPath}/upload/\${data.saveFileName}">`;
				//링크안에 출력하면서 html 형식으로 해석되도록 한다.
				document.querySelector("#profileLink").innerHTML=img;
				//서버에 저장된 프로필 이미지의 이름을 input type="hidden" 의 value 로 넣어준다
				document.querySelector("[name=profile]").value = data.saveFileName;
			});
		});
	</script>
</body>
</html>