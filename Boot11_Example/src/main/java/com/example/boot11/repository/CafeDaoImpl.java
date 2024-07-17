package com.example.boot11.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao{
	
	//의존 객체 Dependency Injection
	@Autowired private SqlSession session;

	@Override
	public List<CafeDto> getList(CafeDto dto) {
		return session.selectList("cafe.getList",dto);
	}

	@Override
	public int getCount(CafeDto dto) {
		return session.selectOne("cafe.getCount",dto);
	}

	@Override
	public void insert(CafeDto dto) {
		session.insert("cafe.insert",dto);
	}

	@Override
	public CafeDto getData(int num) {
		return session.selectOne("cafe.getData",num);
	}

	@Override
	public CafeDto getDetail(CafeDto dto) {
		return session.selectOne("cafe.getDetail",dto);
	}

	@Override
	public void delete(int num) {
		session.delete("cafe.delete",num);
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update",dto);
	}

}
