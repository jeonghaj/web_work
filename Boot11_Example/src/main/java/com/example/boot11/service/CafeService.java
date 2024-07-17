package com.example.boot11.service;

import org.springframework.ui.Model;

import com.example.boot11.dto.CafeDto;

public interface CafeService {
	public void getList(Model model, CafeDto dto);
	public void saveContent(CafeDto dto);
	public void getDetail(Model model, CafeDto dto); //글 자세히 보기를 위한 기능
	public void deleteContent(int num);
	public void getData(Model model, int num); // 글 수정 폼을 출력하기 위한 기능
	public void updateContent(CafeDto dto);
}
