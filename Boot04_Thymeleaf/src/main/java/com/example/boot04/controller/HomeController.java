package com.example.boot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("personToday", "김구라");
		//여기서 리턴한 문자열 앞에는 /templates/ 가 붙고 뒤에는 .html 이 붙어서 /templates/home.html을 가르키게 된다.
		return "home";
	}
}
