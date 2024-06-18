<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>폼 유효성 검증 style 테스트</h1>
		<form action="signup.jsp" method="post">
			<div>
				<label class="form-label" for="nick">닉네임</label> <input
					class="form-control" type="text" name="nick" id="nick" />
				<div class="form-text">영문자 대소문자만 가능 합니다</div>
				<div class="invalid-feedback">사용할 수 없는 닉네임 입니다</div>
				<div class="valid-feedback">사용 가능한 닉네임 입니다</div>
			</div>
			<div>
				<label for="pwd">비밀번호</label> <input type="password"
					class="form-control" name="pwd" id="pwd" />
				<div class="invalid-feedback">비밀 번호를 확인하세요</div>
			</div>
			<div>
				<label for="pwd2">비밀번호 확인</label> <input type="password"
					class="form-control" id="pwd2" />
			</div>
			<button class="btn btn-primary" type="submit" disabled>가입</button>
		</form>
	</div>
	<script>
		//닉네임 유효성 여부를 관리할 변수 
		let isNickValid=false;
		//비밀번호 유효성 여부를 관리할 변수 
		let isPwdValid=false;
		
		/*
			1. 닉네임을 입력했을때 유효성 여부를 변수에 저장한다.
			2. 비밀번호를 입력했을때 유효성 여부를 변수에 저장한다.
			3. 두 변수에 있는 값이 모두 true 일때만 가입 버튼의 disabled 속성을 제거하고 
			   나머지 경우에는 disabled 속성을 추가하는 함수를 미리 만들어 두고 
			4. 적절한 시점에 그 함수를 호출하게 하면 된다.
			
			- disabled 속성 추가 하는 방법
			버튼의 참조값.setAttribute("disabled", "")
			- disabled 속성 제거 하는 방법
			버튼의 참조값.removeAttribute("disabled")
		*/
		
		const checkForm = ()=>{
			//만일 닉네임도 유효하고 그리고 비밀번호도 유효 하다면
			if(isNickValid && isPwdValid){
				//전송 버튼에 disabled 속성을 제거하고 
				document.querySelector("[type=submit]").removeAttribute("disabled");
			}else{
				//전송 버튼에 disabled 속성을 추가한다
				document.querySelector("[type=submit]").setAttribute("disabled", "");
			}
		};
		
		//닉네임을 검증할 정규표현식 객체 
		const reg_nick=/^[a-zA-Z]+$/;
		
		//닉네임을 입력했을때 실행할 함수 등록
		document.querySelector("#nick").addEventListener("input", ()=>{
			//현재까지 입력한 닉네임을 읽어온다.
			let inputNick=document.querySelector("#nick").value;
			//만일 정규 표현식을 통과 하지 못했다면 
			if(!reg_nick.test(inputNick)){
				document.querySelector("#nick").classList.add("is-invalid");
				//사용할수 없는 닉네임이라는 의미에서 false 를 넣어준다.
				isNickValid=false;
				checkForm();
				return;//함수를 여기서 종료해라 
			}
			
			//fetch() 함수를 이용해서 get 방식으로 입력한 닉네임을 보내고 사용가능 여부를 json 으로 응답받는다.
			fetch("check_nick.jsp?nick="+inputNick)
			.then(res=>res.json())
			.then(data=>{
				//일단 클래스를 제거한 후에 
				document.querySelector("#nick").classList.remove("is-valid");
				document.querySelector("#nick").classList.remove("is-invalid");
				//data 는 {canUse:true} or {canUse:false} 형태의 object 이다.
				if(data.canUse){
					document.querySelector("#nick").classList.add("is-valid");
					//사용할수 있는 닉네임이라는 의미에서 true 를 넣어준다.
					isNickValid=true;
				}else{
					document.querySelector("#nick").classList.add("is-invalid");
					//사용할수 없는 닉네임이라는 의미에서 false 를 넣어준다.
					isNickValid=false;
				}
				checkForm();
			});
		});
		//함수를 미리 만들어서 
		const checkPwd = ()=>{
			//양쪽에 입력한 비밀번호를 읽어와서
			let pwd=document.querySelector("#pwd").value;
			let pwd2=document.querySelector("#pwd2").value;
			//양쪽을 같게 입력하면 is-valid 를 추가하고 그렇지 않으면 is-invalid 를 추가한다.
			document.querySelector("#pwd").classList.remove("is-valid");
			document.querySelector("#pwd").classList.remove("is-invalid");
			if(pwd == pwd2){
				document.querySelector("#pwd").classList.add("is-valid");
				//비밀번호가 유효 하다는 의미에서 true 를 넣어준다.
				isPwdValid=true;
			}else{
				document.querySelector("#pwd").classList.add("is-invalid");
				//비밀번호가 유효 하지 않다는 의미에서 false 를 넣어준다.
				isPwdValid=false;
			}
			checkForm();
		};
		
		document.querySelector("#pwd").addEventListener("input", checkPwd);
		
		document.querySelector("#pwd2").addEventListener("input", checkPwd);
	</script>
</body>
</html>