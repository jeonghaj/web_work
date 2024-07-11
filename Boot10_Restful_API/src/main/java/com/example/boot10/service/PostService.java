package com.example.boot10.service;

import java.util.List;

import com.example.boot10.dto.PostDto;

public interface PostService {
	
	//모든 글목록을 리턴하는 메소드
	public List<PostDto> getAll();
	//새로운 글 저장하고 저장된 글 정보를 리턴하는 메소드
	public PostDto addContent(PostDto dto);
	//글을 삭제하고 삭제된 글 정보를 리턴하는 메소드
	public PostDto removeContent(int id);
	//글을 수정하고 수정된 글 정보를 리턴하는 메소드
	public void updateContent(PostDto dto);
	//글 하나의 정보를 리턴하는 메소드
	public PostDto getContent(int id);
}
