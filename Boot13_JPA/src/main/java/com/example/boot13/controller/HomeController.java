package com.example.boot13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	//최상위 경로 요청이 왔을때
	@GetMapping("/")
	public String home() {
		
		// templates/home.html thymeleaf 페이지를 응답하기
		return "home";
	}
}
