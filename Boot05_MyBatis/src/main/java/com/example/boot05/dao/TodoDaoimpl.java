package com.example.boot05.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot05.dto.TodoDto;
@Repository
public class TodoDaoimpl implements TodoDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public List<TodoDto> getList() {
		List<TodoDto> list = session.selectList("todo.getList");
		
		return list;
	}

}
