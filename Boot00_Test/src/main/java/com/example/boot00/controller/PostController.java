package com.example.boot00.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@ResponseBody
	@PostMapping("/posts")
	public PostDto insert( PostDto dto) {
	    PostDto insertedDto = dao.insert(dto);
	    return insertedDto;
	}
	
	@ResponseBody
	@GetMapping("/posts/{num}")
	public PostDto getData(@PathVariable int num) {
		PostDto dto = dao.getData(num);
		return dto;
	}
	
	@ResponseBody
	@DeleteMapping("/posts/{num}")
	public String delete(@PathVariable int num) {
		PostDto dto = dao.delete(num);
		if(dto == null) {
			return "{}";
		}else {
			return null;
		}
	}
	@ResponseBody
	@PutMapping("/posts/{num}")
	public PostDto putMethodName(@PathVariable int num, PostDto dto) {
		dto.setId(num);
		PostDto updated = dao.update(dto);
		return updated ;
	}
	
}
