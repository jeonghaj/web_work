<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/validation/form4.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
</head>
<body>
	<div class="container animate__animated animate__bounceInLeft">
		<h1>폼 유효성 검증 style 테스트</h1>
		<form action="signup.jsp" method="post">
			<div>
				<label class="form-label" for="nick">닉네임</label>
				<input class="form-control" type="text" name="nick" id="nick"/>
				<div class="form-text">영문자 대소문자만 가능 합니다</div>
				<div class="invalid-feedback">사용할 수 없는 닉네임 입니다</div>
				<div class="valid-feedback">사용 가능한 닉네임 입니다</div>
			</div>
			<div>
				<label for="pwd">비밀번호</label>
				<input type="password" class="form-control" name="pwd" id="pwd"/>
				<div class="form-text">특수 문자를 제외한 5글자 이상 10글자 이내로 작성해 주세요</div>
				<div class="invalid-feedback">비밀 번호를 확인하세요</div>
			</div>
			<div>
				<label for="pwd2">비밀번호 확인</label>
				<input type="password" class="form-control" id="pwd2"/>
			</div>
			<div>
				<label class="form-label" for="comment">하고 싶은 말</label>
				<textarea class="form-control animate__animated animate__faster" name="comment" id="comment" rows="5"></textarea>
				<div class="form-text">100 글자 이내로 입력해 주세요</div>
				<div class="form-text">글자 수 : <strong id="textCount">0</strong> </div>
			</div>
			<button class="btn btn-primary" type="submit" disabled>가입</button>
		</form>
	</div>
	<script>
		//닉네임 유효성 여부를 관리할 변수 
		let isNickValid=false;
		//비밀번호 유효성 여부를 관리할 변수 
		let isPwdValid=false;
		//하고싶은말 유효성 여부를 관리할 변수
		let isCommentValid=true;
		
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
			//만일 닉네임도 유효하고 그리고 비밀번호도 유효하고 그리고 하고싶은말도 유효하다면
			if(isNickValid && isPwdValid && isCommentValid){
				//전송 버튼에 disabled 속성을 제거하고 
				document.querySelector("[type=submit]").removeAttribute("disabled");
			}else{
				//전송 버튼에 disabled 속성을 추가한다
				document.querySelector("[type=submit]").setAttribute("disabled", "");
			}
		};
		
		//닉네임을 검증할 정규표현식 객체 
		const reg_nick=/^[a-zA-Z]+$/;
		
		//닉네임을 입력하고 포커스를 다른곳으로 이동했을때 검증 수행하기 ( blur 는 focus 를 잃었을때 발생하는 이벤트 )
		document.querySelector("#nick").addEventListener("blur", ()=>{
			//현재까지 입력한 닉네임을 읽어온다.
			let inputNick=document.querySelector("#nick").value;
			//만일 정규 표현식을 통과 하지 못했다면 
			if(!reg_nick.test(inputNick)){
				document.querySelector("#nick").classList.remove("is-valid");
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
				document.querySelector("#nick").classList.remove("is-valid", "is-invalid");
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
		
		//비밀번호를 검증할 정규 표현식 
		const reg_pwd = /^[a-zA-Z0-9]{5,10}$/; // 특수문자 제외 최소 5 최대 10
		//함수를 미리 만들어서 
		const checkPwd = ()=>{
			//양쪽에 입력한 비밀번호를 읽어와서
			let pwd=document.querySelector("#pwd").value;
			let pwd2=document.querySelector("#pwd2").value;
			
			//입력한 비밀번호가 정규 표현식을 통과하는지 확인한다.
			if(reg_pwd.test(pwd)==false){
				document.querySelector("#pwd").classList.remove("is-valid");
				document.querySelector("#pwd").classList.add("is-invalid");
				isPwdValid=false;
				checkform();
				return;
			}
			
			
			//양쪽을 같게 입력하면 is-valid 를 추가하고 그렇지 않으면 is-invalid 를 추가한다.
			document.querySelector("#pwd").classList.remove("is-valid", "is-invalid");
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
		
		document.querySelector("#comment").addEventListener("input", (e)=>{
			//이 함수에는 발생한 이벤트에 대한 정보를 가지고 있는 event 객체가 매개변수에 전달된다.
			console.log(e);
			//입력한 문자열 읽어오기  (e.target 은 이벤트가 발생한 바로 그 요소의 참조값이다)
			const msg=e.target.value;
			//문자열의 길이
			const length=msg.length;  //e.target.value.length 해서 한번에 읽어올수도 있다 
			//만일 100 글자 초과로 입력했다면 
			if(length > 100){
				e.target.classList.add("is-invalid");
				isCommentValid=false;
				//애니매이션 효과를 주기 위해
				e.target.classList.add("animate__shakeX");
				// "animationend" 이벤트가 일어 났을때 "animate__shakeX" 클래스를 제거 해보세요
				e.target.addEventListener("animationend", (e)=>{
					//클래스 제거 하기 
					e.target.classList.remove("animate__shakeX");
				}, {once:true});
			}else{
				e.target.classList.remove("is-invalid");
				isCommentValid=true;
			}
			//글자수 출력하기
			document.querySelector("#textCount").innerText=length;
			checkForm();
		});
		
		
	</script>
</body>
</html>