package com.example.boot11.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.GalleryDto;

@Repository
public class GalleryDaoImpl implements GalleryDao{
	
	@Autowired SqlSession session;
	//갤러리 정보 추가
	@Override
	public void insert(GalleryDto dto) {
		session.insert("gallery.insert",dto);
	}
	//갤러리 하나의 정보 리턴
	@Override
	public GalleryDto getData(int num) {
		
		return session.selectOne("gallery.getData", num);
	}
	//전체 갤러리의 갯수 리턴
	@Override
	public int getCount() {
		
		return session.selectOne("gallery.getCount");
	}
	//페이징 처리를 고려한 갤러리 목록 리턴
	@Override
	public List<GalleryDto> getList(GalleryDto dto) {

		return session.selectList("gallery.getList",dto);
	}
	//갤러리 정보 삭제
	@Override
	public void delete(int num) {
		session.delete("gallery.delete",num);
	}

}
