package com.example.boot04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.boot04.dto.MemberDto;

@Controller
public class TestController {
	
	@GetMapping("/unescape")
	public String unescape(Model model) {
		//출력할 내용이 때로는 markup 형태 일때도 있다.
		String content="<a href='https://naver.com'>naver</a>";
		model.addAttribute("content",content);
		
		return "sub/unescape";
	}
	
	
	@GetMapping("/inc")
	public String inc() {
		
		return "sub/inc";
	}
	
	
	@GetMapping("/shop/buy")
	public String but(int id, int count) {
		//구매 처리를 하고
		
		//응답하기
		return "sub/buy";
	}
	
	
	@GetMapping("/members")
	public String members(Model model) {
		List<MemberDto> list = new ArrayList<MemberDto>();
		list.add(new MemberDto(1,"김구라","노량진"));
		list.add(new MemberDto(2,"해골","행신동"));
		list.add(new MemberDto(3,"원숭이","동물원"));
		model.addAttribute("list",list);
		
		return "sub/members";
	}
	@GetMapping("/member")
	public String member(Model model) {
		//DB 에서 읽어온 회원 한명의 정보라고 가정
		MemberDto dto = new MemberDto(1,"김구라","노량진");
		//응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("dto",dto);
		//thymeleaf 템플릿 엔진을 이용해서 응답
		return "sub/member";
	}
	
	@GetMapping("/notice")
	public String notice(Model model) {
		//DB에서 읽어온 공지사항이라고 가정
		List<String> list = new ArrayList<String>();
		list.add("장마철 입니다");
		list.add("더위에 주의 하세요");
		list.add("blah..blah..");
		// Thymeleaf 템플릿 엔진에서 사용할 데이터를 Model 객체에 담기
		model.addAttribute("list",list);
		//templates/sub/notice.html 을 해석한 결과를 응답하기
		return "sub/notice";
	}
	
	
	@GetMapping("/sub/play")
	public String play() {
		//templates/sub/play.html 템플릿을 해석한 결과를 응답해라
		return "sub/play";
	}
}
