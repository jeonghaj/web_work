package com.example.boot00.dao;

import java.util.List;

import com.example.boot00.dto.PostDto;

public interface PostDao {
	public PostDto insert(PostDto dto);
	public List<PostDto> getList();
	public PostDto getData(int num);
	public PostDto delete(int num);
	public PostDto update(PostDto dto);
}
