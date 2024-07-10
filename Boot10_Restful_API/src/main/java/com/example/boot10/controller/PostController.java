package com.example.boot10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot10.dao.PostDao;
import com.example.boot10.dto.PostDto;

@RestController // @ResponseBody 의 기능이 모든 메소드에 포함된다.
public class PostController {
	//dao 객체를 주입 받아서 
	@Autowired 
	private PostDao dao;
	
	
	@GetMapping("/posts")
	public List<PostDto> getList(){
		//글 전테 목록을 dao 객체를 이용해서 읽어온다음 
		List<PostDto> list=dao.getList();
		//리턴해준다 
		return list;
	}
	/*
	@PostMapping("/posts")
	public PostDto insert(String title, String author) {
		// @Builder 의 기능을 이용해서 PostDto 객체에 데이터를 담으면서 객체의 참조값 얻어내기 
		PostDto dto=PostDto.builder().title(title).author(author).build();
		//dao 를 이용해서 dto 에 저장된 정보를 DB 에 저장하기 
		dao.insert(dto);
		//현재 추가된 정보를 리턴하기 
		return dto;
	}
	*/
	@PostMapping("/posts")
	public PostDto insert(PostDto dto) { //title & author 가 추출되어서 PostDto 객체에 담긴체로 전달
		//요청 parameter 명과 Dto 의 필드명이 같아야한다
		int id = dao.getSequence();
		dto.setId(id);
		dao.insert(dto);
		return dto;
	}
	
	@GetMapping("/posts/{id}")
	public PostDto getData(@PathVariable("id") int id) {
		return dao.getData(id);
	}
	
	@DeleteMapping("/posts/{id}")
	public PostDto delete(@PathVariable("id") int id) {
		PostDto dto = dao.getData(id);
		dao.delete(id);
		
		return dto;
	}
	
	@PutMapping("/posts/{id}")
	public PostDto update(@PathVariable("id") int id, PostDto dto) {
		dto.setId(id);
		dao.update(dto);
		return dto;
	}
}

