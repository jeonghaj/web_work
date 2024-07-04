package com.example.boot05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot05.dao.MemberDao;
import com.example.boot05.dto.MemberDto;

@Controller
public class MemberController {
	//spring bean container 로 부터 MemberDao type 주입(DI) 받기
	@Autowired
	private MemberDao dao;
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) { // 수정할 회원의 정보가 자동으로 추출되어서 MemberDto 객체에 담겨서 전달된다.
		dao.update(dto);
		return "member/update";
	}
	
	@GetMapping("/member/updateform")
	public String updateForm(int num,Model model) { //수정할 회원의 번호가 자동으로 추출되어서 num 매개변수에 전달
		//수정할 회원의 번호를 이용해서 회원정보를 얻어온다.
		MemberDto dto = dao.getData(num);
		//얻어온 회원정보를 Model 객체에 담는다.
		model.addAttribute("dto",dto);
		//회원정보 수정폼을 응답한다.
		return "member/updateform";
	}
	
	@GetMapping("/member/delete")
	public String delete(int num) {//삭제할 번호가 자동으로 추출되어서 num 매개변수에 전달된다.
		//MemberDto 객체를 이용해서 삭제하고
		dao.delete(num);
		//회원목록 보기로 리다일렉트 이동하라는 응답하기	
		return "redirect:/member/list" ;
	}
	
	@PostMapping("/member/insert")
	public String insert(MemberDto dto) { //요청 파라미터가 추출되어서 MemberDto 객체에 담겨서 전달된다.
		//MemberDto 객체를 이용해서 DB 에 저장하고
		dao.insert(dto);
		//응답하기
		return "member/insert";
	}
	
	@GetMapping("/member/insertform")
	public String insertForm() {
		
		return "member/insertform";
	}
	
	
	@GetMapping("/member/list")
	public String list(Model model) {
		//DB 에서 회원 목록(list<MemberDto>)을 얻어와서 Model 객체에 담고
		List<MemberDto> list = dao.getList();
		model.addAttribute("list",list);
		// thymeleaf view 페이지를 이용해서 응답하기
		
		return "member/list";
	}
}
