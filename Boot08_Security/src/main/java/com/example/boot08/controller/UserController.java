package com.example.boot08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
		
		//세션 허용갯수 초과시
		@GetMapping("/user/expired")
		public String userExpired() {
			return "user/expired";
		}
		
		//권한 부족시 or 403 인 경우
		//@RequestMapping => GET/ POST 등 모두 가능
		@RequestMapping("/user/denied")
		public String userDenied() {
			return "user/denied";
		}
		
		//ROLL_STAFF, ROLL_ADMIN 만 요청 가능
		@GetMapping("/staff/user/list")
		public String userList() {
			return "user/list";
		}
		//ROLE_ADMIN 만 요청가능
		@GetMapping("admin/user/manage")
		public String userManage() {
			return "user/manage";
		}

		@GetMapping("/user/loginform")
		public String loginform() {
			//templates/user/loginform.html 페이지로 forward 이동해서 응답
			return "user/loginform";
		}
		
		//로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리다일렉트 되는 요청경로
		@GetMapping("/user/required_loginform")
		public String required_loginform() {
			return "user/required_loginform";
		}
		
		//POST 방식 /user/login 요청 후 로그인 성공인경우 forward 이동될 url
		@PostMapping("/user/login_success")
		public String loginfSuccess() {
			return "user/login_success";
		}
		//로그인 폼을 제출(POST) 한 로그인 프로세스 중에 forward 되는 경로이기 떄문에 @PostMapping 임에 주의
		@PostMapping("/user/login_fail")
		public String loginFail() {
			return "user/login_fail";
		}
		
}
