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
	public List<FileDto> getList(FileDto dto) {	
		// FileDto 에는 보여줄 page 에 해당하는 startRowNum 과 endRowNum 이 들어있다.
		return session.selectList("file.getList",dto);
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
	public int getCount(FileDto dto) {
		// dto 에 condition 과 keyword 는 null 일수도 있고 아닐수도 있다.
		return session.selectOne("file.getCount",dto);
	}

}
