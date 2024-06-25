<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/pwd_updateform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
	<div class="container" id="app">
		<h1>비밀 번호 수정 페이지</h1>
		<form action="pwd_update.jsp" method="post" id="myForm">
			<div class="mb-2">
				<label class="form-label" for="pwd">기존 비밀번호</label>
				<input class="form-control" 
					@input="onPwdInput" 
					v-bind:class="{'is-invalid': !isPwdValid && isPwdDirty, 'is-valid':isPwdValid}" type="password" name="pwd" id="pwd"/>
				<div class="invalid-feedback">반드시 입력하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="newPwd">새 비밀번호</label>
				<input class="form-control" type="password" name="newPwd" id="newPwd"
					@input="onNewPwdInput" 
					v-model="newPwd"
					v-bind:class="{'is-invalid': !isNewPwdValid && isNewPwdDirty, 'is-valid':isNewPwdValid}"/>
				<small class="form-text">반드시 입력하고 아래의 확인란과 동일해야 합니다</small>
				<div class="invalid-feedback">새 비밀번호를 확인하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="newPwd2">새 비밀번호 확인</label>
				<input class="form-control" type="password" id="newPwd2"
					@input="onNewPwdInput" v-model="newPwd2"/>
			</div>
			<button class="btn btn-success" type="submit" v-bind:disabled="!isPwdValid || !isNewPwdValid">수정하기</button>
			<button class="btn btn-danger" type="reset">리셋</button>		
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script>
		new Vue({
			el:"#app",
			data:{
				isPwdValid:false,
				isNewPwdValid:false,
				newPwd:"",
				newPwd2:"",
				isPwdDirty:false,  //비밀번호 입력란에 한번이라도 입력했는지 여부
				isNewPwdDirty:false //새비밀번호 입력한에 한번이라도 입력했는지 여부 
			},
			methods:{
				onPwdInput(e){
					//현재까지 입력한 비밀번호
					const pwd=e.target.value;
					//공백이 아닌 한글자가 한번이상 반복 되어야 통과 되는 정규표현식
					const reg_pwd=/[\S]+/;
					if(reg_pwd.test(pwd)){
						this.isPwdValid=true;
					}else{
						this.isPwdValid=false;
					}
					this.isPwdDirty=true;
				},
				onNewPwdInput(){
					//공백이 아닌 글자를 하나이상 입력했는지 확인할 정규 표현식
					const reg_pwd=/[\S]+/;
					//만일 정규표현식도 통과하고 그리고 두개의 비밀번호가 같다면 
					if(reg_pwd.test(this.newPwd) && (this.newPwd === this.newPwd2)){
						//새 비밀번호 유효성 여부를 true 로 변경
						this.isNewPwdValid = true;
					}else{//그렇지 않다면
						//새 비밀번호 유효성 여부를 false 로 변경 
						this.isNewPwdValid = false;
					}
					this.isNewPwdDirty=true;
				}
			}
		});
	</script>
</body>
</html>