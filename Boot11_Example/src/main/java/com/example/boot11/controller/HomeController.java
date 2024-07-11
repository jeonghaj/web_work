package com.example.boot11.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
	//최상위 경로 요청에 대해서 응답할 컨트롤러 메소드
	@GetMapping("/")
	public String home(Model m) { //컨트롤러 메소드에서 필요한 객체가 있으면 매개변수에 선언한다.
		//응답에 필요한 데이터는 HttpServletRequest 에 담거나 혹은 Model 객체에 담으면 view engine 에서 사용 가능
		//DB 에서 읽어온 공지사항 이라고 가정하자
		List<String> notice = Arrays.asList("공지1","공지2","공지3");
		//어떤 key 값으로 어떤 type 데이터를 담았는지 기억을 하고 템플릿 페이지를 만들어야한다.
		m.addAttribute("notice",notice);
		// /templates/home.html 타임리프 페이지로 응답.
		return "home";
	}
}
