<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form2.jsp</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container" id="app">
		<h3>회원가입 폼 입니다</h3>
		<%-- 웹브라우저의 자체 폼 검증기능 비활성화  <form  novalidate> --%>
		<form action="signup.jsp" method="post" novalidate>
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label> <input
					@blur="onIdInput"
					:class="{'is-valid':isIdValid,'is-invalid':!isIdValid && isIdDirty}"
					class="form-control" type="text" name="id" id="id" /> <small
					class="form-text">영문자 소문자로 시작하고 5글자~10글자 이내로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디 입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label> <input
					@input="onPwdInput"
					:class="{'is-valid':isPwdValid,'is-invalid':!isPwdValid && isPwdDirty}"
					v-model="pwd" class="form-control" type="password" name="pwd"
					id="pwd" /> <small class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label> <input
					@input="onPwdInput" v-model="pwd2" class="form-control"
					type="password" id="pwd2" />
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label> <input
					@input="onEmailInput"
					:class="{'is-valid':isEmailValid, 'is-invalid': !isEmailValid && isEmailDirty}"
					class="form-control" type="email" name="email" id="email" />
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요.</div>
			</div>
			<!-- 
				가입 버튼이 disabled 되는 조건은
				아이디가 유효하지 않거나 또는 비밀번호가 유요하지 않거나 또는 이메일이 유요하지 않으면.
			 -->
			<button v-bind:disabled="!isIdValid || !isPwdValid || isEmailValid"
				class="btn btn-primary btn-sm" type="submit">가입</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script>
		new Vue({
			el:"#app",
			data:{
				isEmailValid:false,
				isEmailDirty:false,
				isIdValid:false,
				isIdDirty:false,
				isPwdValid:false,
				isPwdDirty:false,
				pwd:"",
				pwd2:""
			},
			methods:{
				onPwdInput(e){
					this.isPwdDirty=true;
					//비밀 번호를 검증할 정규 표현식(특수문자 포함여부)
					const reg_pwd=/[\W]/;
					//일단 정규표현식을 만족하는지 확인해서 만족하지 않으면 함수를 여기서 종료
					//만일 첫번째 비밀번호가 정규표현식을 통과하지 못하거나 또는 두번째 비밀번호가 정규표현식을 통과하지 못한다면
					if( !reg_pwd.test(this.pwd) || !reg_pwd.test(this.pwd2) ){
						this.isPwdValid=false;
						return;
					}
					if(this.pwd == this.pwd2){
						//비밀번호가 유효 하다는 의미에서 true 를 넣어준다.
						this.isPwdValid=true;
					}else{
						//비밀번호가 유효 하지 않다는 의미에서 false 를 넣어준다.
						this.isPwdValid=false;
					}
				},
				onIdInput(e){
					this.isIdDirty=true;
					//현재까지 입력한 아이디를 읽어온다.
					let inputId=e.target.value;
					//아이디를 검증할 정규표현식 객체 
					const reg_id=/^[a-z].{4,9}$/;
					//만일 정규 표현식을 통과 하지 못했다면 
					if(!reg_id.test(inputId)){
						this.isIdValid=false;
						return;//함수를 여기서 종료해라 
					}
					//fetch() 함수를 이용해서 get 방식으로 입력한 아이디를 보내고 사용가능 여부를 json 으로 응답받는다.
					fetch("${pageContext.request.contextPath}/user/check_id.jsp?id="+inputId)
					.then(res=>res.json())
					.then(data=>{
						//data 는 {canUse:true} or {canUse:false} 형태의 object 이다.
						if(data.canUse){
							this.isIdValid=true;
						}else{
							this.isIdValid=false;
						}
					});
				},
				onEmailInput(e){
					//한번이라도 입력하면 이메일 더럽혀짐 여부를 true 로 바꿔준다.
					this.isEmailDirty=true;
					//입력한 문자열(이메일) 읽어오기  (e.target 은 이벤트가 발생한 바로 그 요소의 참조값이다)
					const email=e.target.value;
					//이메일을 검증할 정규 표현식
					const reg_email=/@/;
					//정규표현식에 매칭되는지 여부
					if(reg_email.test(email)){
						this.isEmailValid=true;
					}else{
						this.isEmailValid=false;
					}
				}
			}
		});
	</script>
</body>
</html>













