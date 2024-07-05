package com.example.boot06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@GetMapping("/user/loginform")
	public String loginform() {
		return "user/loginform";
	}
	
	@PostMapping("/user/login")
	public String login(String id, String pwd, HttpSession session) {
		//원래는 DB 내용을 읽어와서 로그인 처리를 해야 하지만 예시
		session.setAttribute("id", id);
		//로그인 후 최상위 경로로 리다일렉트
		return "redirect:/";
	}
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		//세션 초기화 // 세션에 저장된 id 정보 없앰+
		session.invalidate();
		return "redirect:/";
	}
}
