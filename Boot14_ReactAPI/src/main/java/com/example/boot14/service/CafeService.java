package com.example.boot14.service;

import java.util.Map;

import org.springframework.ui.Model;

import com.example.boot14.dto.CafeCommentDto;
import com.example.boot14.dto.CafeDto;

public interface CafeService {
	
	public Map<String, Object> getList(CafeDto dto);

	public void saveContent(CafeDto dto);

	public Map<String, Object> getDetail(CafeDto dto); // 글 자세히 보기를 위한 기능

	public void deleteContent(int num);

	public void getData(Model model, int num); // 글 수정을 폼을 출력하기 위한 기능

	public void updateContent(CafeDto dto);

	public CafeCommentDto saveComment(CafeCommentDto dto); // 댓글 추가하는 기능
	// dto 에 담긴 페이지 번호에 해당하는 댓글 목록을 리턴해주는 메소드

	public Map<String, Object> getCommentList(CafeCommentDto dto);

	public void deleteComment(int num);

	public void updateComment(CafeCommentDto dto);
}
