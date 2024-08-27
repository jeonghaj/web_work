package com.example.boot14.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot14.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao{
	
	@Autowired private SqlSession session;
	
	@Override
	public int getSequence() {
		return session.selectOne("cafeComment.getSequence");
	}

	@Override
	public void insert(CafeCommentDto dto) {
		session.insert("cafeComment.insert",dto);		
	}

	@Override
	public List<CafeCommentDto> getList(CafeCommentDto dto) {
		return session.selectList("cafeComment.getList",dto);
	}

	@Override
	public void delete(int num) {
		session.delete("cafeComment.delete",num);
	}

	@Override
	public CafeCommentDto getData(int num) {
		return session.selectOne("cafeComment.getData",num);
	}

	@Override
	public void update(CafeCommentDto dto) {
		session.update("cafeComment.update",dto);
	}

	@Override
	public int getCount(int ref_group) {
		return session.selectOne("cafeComment.getCount",ref_group);
	}

}
