package com.example.boot08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@ResponseBody
	@GetMapping("/play")
	public String play() {
		return "let's play";
	}
	
	@ResponseBody
	@GetMapping("/study")
	public String study() {
		return "let's study";
	}
}
