package com.example.boot14.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot14.dto.CafeCommentDto;
import com.example.boot14.dto.CafeDto;
import com.example.boot14.repository.CafeCommentDao;
import com.example.boot14.repository.CafeDao;

@Service
public class CafeServiceImpl implements CafeService{
	
	@Autowired private CafeDao cafeDao;
	@Autowired private CafeCommentDao cafeCommentDao;
	
	@Override
	public Map<String, Object> getList(CafeDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveContent(CafeDto dto) {
		//글작성자는 spring security 에서 1회성 인증을 받은 userName 이다
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//글작성자를 dto 에 담는다
		dto.setWriter(userName);
		//DB 에 저장
		cafeDao.insert(dto);
	}

	@Override
	public Map<String, Object> getDetail(CafeDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteContent(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getData(Model model, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateContent(CafeDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CafeCommentDto saveComment(CafeCommentDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getCommentList(CafeCommentDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		// TODO Auto-generated method stub
		
	}

	
}
