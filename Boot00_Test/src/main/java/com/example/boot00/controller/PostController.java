package com.example.boot00.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot00.dao.PostDao;
import com.example.boot00.dto.PostDto;

@Controller
public class PostController {
	
	@Autowired
	private PostDao dao;
	
	@ResponseBody
	@GetMapping("/posts")
	public List<PostDto> getList(){
		List<PostDto> list = dao.getList();
		return list;
	}
	
	@PostMapping("/posts")
	public String insert(PostDto dto) {
		dao.insert(dto);
		
		return "redirect:/posts";
	}
	
	@ResponseBody
	@GetMapping("/posts/{num}")
	public PostDto getData(@PathVariable int num) {
		PostDto dto = dao.getData(num);
		return dto;
	}
	
	
}
