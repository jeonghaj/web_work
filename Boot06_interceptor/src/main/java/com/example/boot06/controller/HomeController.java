package com.example.boot06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("fortuneToday", "go EAST");
		
		return "home";
	}
	
	@GetMapping("/sub/play")
	public String play() {
		return "sub/play";
	}
	
	@GetMapping("/sub/study")
	public String study() {
		return "sub/study";
	}
}
