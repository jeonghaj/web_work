package com.example.boot11.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot11.dto.CafeCommentDto;
import com.example.boot11.dto.CafeDto;
import com.example.boot11.service.CafeService;

@Controller
public class CafeController {
	
	@Autowired private CafeService service;
	
	@GetMapping("/cafe/comment_list")
	public String commentList(Model model, CafeCommentDto dto) {
		//CafeCommentDto 에는 pageNum, ref_group 이 들어있다 (GET 방식 파라미터)
		service.getCommentList(model, dto);
		
		try {
			//테스트를 위해 임의로 스레드를 3초 지연
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// template/cafe/comment_list.html 에서 댓글이 들어있는 여러개의 li 를 응답할 예정
		return "cafe/comment_list";
	}
	
	@ResponseBody
	@PostMapping("/cafe/comment_update")
	public Map<String, Object> commentUpdate(CafeCommentDto dto){
		service.updateComment(dto);
		//수정된 글의 정보를 json 으로 응답
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		return map;
	}
	
	@ResponseBody //Map 객체를 리턴하면 json 문자열이 응답되도록 어노테이션 추가
	@GetMapping("/cafe/comment_delete")
	public Map<String,Object> commentDelete(int num){
		//num 은 GET 방식 파라미터로 전달되는 삭제할 댓글의 번호
		service.deleteComment(num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	
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
