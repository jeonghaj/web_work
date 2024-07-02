package com.example.boot02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot02.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SendController {
	
	//컨트롤러 메소드 안에서 HttpServletRequest, HttpServletResponse, HttpSession 등의 객체가 필요하면
	//매개변수에 선언하면 된다.
	
	//선언만 하면 Spring 프레임워크가 알아서 해당객체의 참조값을 매개변수에 전달해 준다.
	@ResponseBody
	@PostMapping("/send")
	public String send(HttpServletRequest request) {
		//요청 파라미터 추출하기
		String msg = request.getParameter("msg");
		System.out.println("msg :"+msg);
		
		return "/send okay!";
	}
	
	//전송되는 파라미터명과 동일하게 매개 변수를 선언하면 자동으로 추출되어서 매개변수에 전달된다.
	@ResponseBody
	@PostMapping("/send2")
	public String send2(String msg) {
		System.out.println("msg:"+msg);
		
		return "/send2 okay!";
	}
	
	@ResponseBody
	@PostMapping("/send3")
	public String send3(@RequestParam(defaultValue = "0")int num,
			@RequestParam(defaultValue = "아무개")String name) {
		System.out.println(String.format("번호:%d 이름:%s", num, name ));
		
		return "/send3 okay!";
	}
	
	//전송되는 파라미터명과 동일한 필드명을 가지고 있는 Dto type 으로 매개 변수를 선언하면 
	// Dto 객체가 자동으로 생성되고 생성된 객체에 전송된 파라미터 값에 담은 다음 그 참조값을
	//매개 변수에 전달해 준다.
	@ResponseBody
	@PostMapping("/send4")
	public String send4(MemberDto dto) {
		System.out.println(String.format("번호:%d 이름:%s", dto.getNum(), dto.getName()));
		
		return "/send4 okay!";
	}
}
