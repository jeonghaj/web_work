package com.example.boot02.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot02.dto.MemberDto;
/*
 *  클라이언트의 요청을 처리할 컨트롤러를 정의하고 bean 으로 만들기 
 */
@Controller
public class HomeController {
	
	@ResponseBody
	@GetMapping("/members")
	public List<MemberDto> members(){
		List<MemberDto> members = new ArrayList<MemberDto>();
		members.add(new MemberDto(1,"김구라","노량진"));
		members.add(new MemberDto(2,"해골","행신동"));
		members.add(new MemberDto(3,"원숭이","동물원"));
		//List<MemberDto> 객체를 리턴하면 [{},{},...] 형식의 JSON 문자열이 응답된다.
		return members;
	}
	
	@ResponseBody
	@GetMapping("/friends")
	public List<String> friends(){
		List<String> names = new ArrayList<String>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		return names;
	}
	
	
	//@ResponseBody 어노테이션이 붙어있는 메소드에서 Dto 객체를 리턴하면
	//Dto 에 담긴 정보가 JSON 문자열로 변환되어서 클라이언트에게 응답
	@ResponseBody
	@GetMapping("/member2")
	public MemberDto member2() {
		MemberDto dto = new MemberDto();
		dto.setNum(1);
		dto.setName("김구라");
		dto.setAddr("노량진");
		return dto;
	}
	
	//@ResponseBody 어노테이션이 붙어 있는 메소드에서 map 객체를 리턴하면
	//Map 에 담긴 정보가 JSON 문자열로 변환되어서 클라이언트에게 응답
	@ResponseBody
	@GetMapping("/member")
	public Map<String, Object> member(){
		//DB에서 읽어온 회원 한명의 정보라고 가정하자
		Map<String, Object> map = new HashMap<>();
		map.put("num",1);
		map.put("name","김구라");
		map.put("isMan",true);
		
		return map;
	}
	
	@ResponseBody
	@GetMapping("/hello") //클라이언트가 "/hello" 경로로 요청을 하면 이 메소드가 실행된다.
	public String hello() {
		
		// String type 을 리턴하면서 메소드에 @ResponseBody 어노테이션을 붙여 놓으면 
		// 여기서 리턴한 문자열이 클라이언에게 그대로 출력된다.
		return "Nice to meet you!";
	}
	
	@ResponseBody
	@GetMapping("/fortune")
	public String fortune() {
		
		return "동쪽으로 가면 귀인을 만나요";
	}
	
	
}














