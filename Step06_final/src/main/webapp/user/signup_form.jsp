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
		<h3>회원가입 폼 입니다</h3>
		<%-- 웹브라우저의 자체 폼 검증기능 비활성화 <form novalidate> --%>
		<form action="signup.jsp" method="post" novalidate>
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label> <input
					class="form-control" type="text" name="id" id="id" /> <small
					class="form-text">영문자 소문자로 시작하고 5글자~10글자 이내로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디 입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label> <input
					class="form-control" type="password" name="pwd" id="pwd" /> <small
					class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label> <input
					class="form-control" type="password" id="pwd2" />
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label> <input
					class="form-control" type="email" name="email" id="email" />
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요.</div>
			</div>
			<button class="btn btn-primary btn-sm" type="submit" disabled>가입</button>
		</form>
	</div>
	<script>
	//아이디 유효성 여부를 관리할 변수 
	let isIdValid=false;
	//비밀번호 유효성 여부를 관리할 변수 
	let isPwdValid=false;
	//이메일 유효성 여부를 관리할 변수
	let isEmailValid=false;
	
	const checkForm = ()=>{
		//만일 아이디도 유효하고 그리고 비밀번호도 유효하고 그리고 비밀번호도 유효하다면
		if(isIdValid && isPwdValid && isEmailValid){
			//전송 버튼에 disabled 속성을 제거하고 
			document.querySelector("[type=submit]").removeAttribute("disabled");
		}else{
			//전송 버튼에 disabled 속성을 추가한다
			document.querySelector("[type=submit]").setAttribute("disabled", "");
		}
	};
	
	//아이디를 검증할 정규표현식 객체 
	const reg_id=/^[a-z].{4,9}$/;
	
	//아이디를 입력하고 포커스를 다른곳으로 이동했을때 검증 수행하기 ( blur 는 focus 를 잃었을때 발생하는 이벤트 )
	document.querySelector("#id").addEventListener("blur", ()=>{
		//현재까지 입력한 아이디를 읽어온다.
		let inputId=document.querySelector("#id").value;
		//만일 정규 표현식을 통과 하지 못했다면 
		if(!reg_id.test(inputId)){
			document.querySelector("#id").classList.add("is-invalid");
			//사용할수 없는 아이디라는 의미에서 false 를 넣어준다.
			isIdValid=false;
			checkForm();
			return;//함수를 여기서 종료해라 
		}
		
		//fetch() 함수를 이용해서 get 방식으로 입력한 아이디를 보내고 사용가능 여부를 json 으로 응답받는다.
		fetch("${pageContext.request.contextPath}/user/check_id.jsp?id="+inputId)
		.then(res=>res.json())
		.then(data=>{
			//일단 클래스를 제거한 후에 
			document.querySelector("#id").classList.remove("is-valid", "is-invalid");
			//data 는 {canUse:true} or {canUse:false} 형태의 object 이다.
			if(data.canUse){
				document.querySelector("#id").classList.add("is-valid");
				//사용할수 있는 아이디라는 의미에서 true 를 넣어준다.
				isIdValid=true;
			}else{
				document.querySelector("#id").classList.add("is-invalid");
				//사용할수 없는 아이디라는 의미에서 false 를 넣어준다.
				isIdValid=false;
			}
			checkForm();
		});
	});
	/*
	-정규표현식에서 줄인 표현이 있다.
	
	특수문자가 아닌 한글자[a-zA-Z0-9] => [\w]
	특수문자 한글자[^a-zA-Z0-9] => [\W]
	숫자 한글자[0-9] => [\d]
	숫자가 아닌 한글자[^0-9] => [\D}
	공백 한글자 =>[ ] [\s]
	공백이 아닌 한글자 => [^ ] => [\S]
	*/
	
	//비밀 번호를 검증할 정규 표현식(특수문자 포함여부)
	const reg_pwd=/[\W]/;
	
	//함수를 미리 만들어서 
	const checkPwd = ()=>{
		//양쪽에 입력한 비밀번호를 읽어와서
		let pwd=document.querySelector("#pwd").value;
		let pwd2=document.querySelector("#pwd2").value;
		
		//일단 is-valid 와 is-invalid 클래스를 제거를 먼저하고 
		document.querySelector("#pwd").classList.remove("is-valid", "is-invalid");
		
		//일단 정규표현식을 만족하는지 확인해서 만족하지 않으면 함수를 여기서 종료
		//만일 첫번째 비밀번호가 정규표현식을 통과하지 못하거나 또는 두번째 비밀번호가 정규표현식을 통과하지 못한다면
		if( !reg_pwd.test(pwd) || !reg_pwd.test(pwd2) ){
			document.querySelector("#pwd").classList.add("is-invalid");
			isPwdValid=false;
			checkForm();
			return;
		}
		
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
	
	//이메일을 검증할 정규 표현식
	const reg_email=/@/;
	
	document.querySelector("#email").addEventListener("input", (e)=>{
		//이 함수에는 발생한 이벤트에 대한 정보를 가지고 있는 event 객체가 매개변수에 전달된다.
		console.log(e);
		//입력한 문자열 읽어오기  (e.target 은 이벤트가 발생한 바로 그 요소의 참조값이다)
		const email=e.target.value;
		
		//일단 is-valid 와 is-invalid 클래스를 제거를 먼저하고 
		document.querySelector("#email").classList.remove("is-valid", "is-invalid");
		
		if(reg_email.test(email)){
			isEmailValid=true;
			document.querySelector("#email").classList.add("is-valid");
		}else{
			document.querySelector("#email").classList.add("is-invalid");
			isEmailvalid=false;
		}
		
		checkForm();
	});		
	</script>
</body>
</html>