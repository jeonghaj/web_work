package com.example.boot00.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.boot00.dao.PostDao;
import com.example.boot00.dto.PostDto;

@Controller
public class PostController {
	
	@Autowired
	private PostDao dao;
	
	@GetMapping("/posts")
	public List<PostDto> getList(){
		dao.getList();
	}
	
}
