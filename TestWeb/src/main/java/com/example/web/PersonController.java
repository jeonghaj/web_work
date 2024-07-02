package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//클라이언트의 요청을 처리할 컨트롤러 만들기
@Controller //컨트롤러 역할을 할 수 있는 bean 만들기
public class PersonController {
	
	//특정 경로 요청을 처리할 메소드 만들기
	@ResponseBody // 리턴하는 데이터를 클라이언트에게 출력하도록 하는 어노테이션
	@GetMapping("/person") //GET 방식 /person 요청이 왔을때 이 메소드가 호출되도록 설정
	public String person() {
		return "오늘의 인물은 김구라 입니다";
	}
}
