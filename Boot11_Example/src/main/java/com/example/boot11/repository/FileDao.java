package com.example.boot11.repository;

import java.util.List;

import com.example.boot11.dto.FileDto;

public interface FileDao {
	public void insert(FileDto dto); // 파일 정보 저장
	public List<FileDto> getList(FileDto dto); //pageNum 에 해당하는 파일 목록 리턴하기
	public void delete(int num); // 파일 하나의 정보 삭제
	public FileDto getData(int num); // 파일 하나의 정보 리턴
	public int getCount(FileDto dto); // 검색 조건에 맞는 글의 갯수를 리턴하는 메소드
}
