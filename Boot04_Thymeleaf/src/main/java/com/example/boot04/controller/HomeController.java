package com.example.boot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("personToday", "김구라");
		
		//GET 방식 파라미터 출력 연습을 위해서 Model 객체에 미리 정보를 담아두기
		model.addAttribute("id",999);
		model.addAttribute("count",3);
		
		//여기서 리턴한 문자열 앞에는 /templates/ 가 붙고 뒤에는 .html 이 붙어서 /templates/home.html을 가르키게 된다.
		return "home";
	}
}
