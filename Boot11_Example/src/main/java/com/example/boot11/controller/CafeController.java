package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot11.dto.CafeCommentDto;
import com.example.boot11.dto.CafeDto;
import com.example.boot11.service.CafeService;

@Controller
public class CafeController {
	
	@Autowired private CafeService service;
	// CafeCommentDto 에는 reg_group, target_id, content 3개의 정보가 들어있다.
	//( 대댓글인 경우에는 comment_group 번호도 같이 넘어온다.)
	@PostMapping("/cafe/comment_insert")
	public String commentInsert(CafeCommentDto dto) {
		//댓글 저장 처리를 하고
		service.saveComment(dto);
		//해당글 자세히 보기로 다시 리다일렉트 시킨다
		return "redirect:/cafe/detail?num="+dto.getRef_group();
	}
	
	@PostMapping("/cafe/update")
	public String update(CafeDto dto) {
		//서비스 객체를 이용해서 수정을 반영
		service.updateContent(dto);
		//해당 글 자세히보기로 리다일렉트 이동(GET 방식 parameter 로 글번호도 전달해야한다)
		return "redirect:/cafe/detail?num="+dto.getNum();
	}
	
	@GetMapping("/cafe/updateform")
	public String updateForm(Model model,int num) {
		service.getData(model, num);
		return "cafe/updateform";
	}
	
	@GetMapping("/cafe/delete")
	public String delete(int num) {
		service.deleteContent(num);
		return "redirect:/cafe/list";
	}
	
	@GetMapping("/cafe/detail")
	public String detail(Model model,CafeDto dto) {
		service.getDetail(model, dto);
		return "cafe/detail";
	}
	
	@GetMapping("/cafe/list")
	public String list(Model model, CafeDto dto) {
		//pageNum 또는 keyword 가 파라미터로 전달된다면 dto 에 들어있다.
		service.getList(model, dto);
		return "cafe/list";
	}
	
	@GetMapping("/cafe/insertform")
	public String insertForm() {
		return "cafe/insertform";
	}
	
	@PostMapping("/cafe/insert")
	public String insert(CafeDto dto) {
		service.saveContent(dto);
		return "cafe/insert";
	}
}
