package com.example.boot11.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.FileDto;

@Repository // bean 으로 만들기 위해
public class FileDaoImpl implements FileDao{
	//mybatis 기반의 dao 에서 필요한 객체
	@Autowired private SqlSession session;
	
	@Override
	public void insert(FileDto dto) {
		session.insert("file.insert",dto);
	}

	@Override
	public List<FileDto> getList() {	
		return session.selectList("file.getList");
	}

	@Override
	public void delete(int num) {
		session.delete("file.delete",num);
	}

	@Override
	public FileDto getData(int num) {
		return session.selectOne("file.getData",num);
	}

	@Override
	public int getCount() {
		return session.selectOne("file.getCount");
	}

}
