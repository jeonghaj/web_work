package com.example.boot00.dao;

import java.util.List;

import com.example.boot00.dto.PostDto;

public interface PostDao {
	public void insert(PostDto dto);
	public List<PostDto> getList();
}
