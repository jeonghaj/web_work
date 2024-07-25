package com.example.boot11.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.GalleryDto;

@Repository
public class GalleryDaoImpl implements GalleryDao{
	
	@Autowired SqlSession session;
	
	@Override
	public void upload(GalleryDto dto) {
		session.insert("gallery.insert",dto);
	}

	@Override
	public List<GalleryDto> getList() {

		return session.selectList("gallery.getList");
	}

	@Override
	public GalleryDto getData(int num) {
		
		return session.selectOne("gallery.getData", num);
	}

}
