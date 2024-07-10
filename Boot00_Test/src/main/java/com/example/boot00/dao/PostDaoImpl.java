package com.example.boot00.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot00.dto.PostDto;

@Repository
public class PostDaoImpl implements PostDao{
	
	@Autowired 
	private SqlSession session;
	
	@Override
	public PostDto insert(PostDto dto) {
		session.insert("post.insert",dto);
	   
	    int num = session.selectOne("post.selectLastInsertedId");

	    PostDto insertedPost = session.selectOne("post.getData", num);

	    return insertedPost;
	}

	@Override
	public List<PostDto> getList() {
		List<PostDto> list = session.selectList("post.getList");
		return list;
	}

	@Override
	public PostDto getData(int num) {
		PostDto dto = session.selectOne("post.getData", num);
		return dto;
	}
	
	@Override
	public PostDto delete(int num) {
		session.delete("post.delete",num);
		PostDto deletedPost = session.selectOne("post.getData",num);
		return deletedPost;
	}

	@Override
	public PostDto update(PostDto dto) {
		session.update("post.update",dto);
		int num = dto.getId();
		PostDto updatedPost = session.selectOne("post.getData",num);
		return updatedPost;
	}

}
