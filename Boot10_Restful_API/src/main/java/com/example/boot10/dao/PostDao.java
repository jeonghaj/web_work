package com.example.boot10.dao;

import java.util.List;

import com.example.boot10.dto.PostDto;

public interface PostDao {
	public List<PostDto> getList();
	public PostDto getData(int id);
	public void insert(PostDto dto);
	public void update(PostDto dto);
	public void delete(int id);
	//저장할 글번호를 미리 얻어내서 리턴하는 메소드
	public int getSequence();
}
