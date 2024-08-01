package com.example.boot13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot13.dto.MemberDto;
import com.example.boot13.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	@PostMapping("/member/update")
	public String update(MemberDto dto) {
//		service.update(dto);
		service.update2(dto);
		return "member/update";
	}

	@GetMapping("/member/updateform")
	public String updateform(Long num, Model model) {
		service.getData(num, model);
		return "member/updateform";
	}

	@GetMapping("/member/delete")
	public String delete(Long num) {
		service.delete(num);
		return "redirect:/member/list";
	}

	@PostMapping("/member/insert")
	public String insert(MemberDto dto) {
		// MemberService 를 이용해서 회원 정보 추가
		service.insert(dto);
		return "member/insert";
	}

	@GetMapping("/member/insertform")
	public String insertform() {

		return "member/insertform";
	}

	@GetMapping("/member/list")
	public String list(Model model) {
		service.getList(model);
		return "member/list";
	}
}
