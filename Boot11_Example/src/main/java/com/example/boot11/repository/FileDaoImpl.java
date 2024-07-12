package com.example.boot11.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.FileDto;

@Repository
public class FileDaoImpl implements FileDao{
	
	@Autowired private SqlSession session;
	
	@Override
	public void upload(FileDto dto) {
		session.insert("file.upload",dto);
	}

}
