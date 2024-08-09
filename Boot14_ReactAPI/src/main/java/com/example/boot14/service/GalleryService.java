package com.example.boot14.service;

import java.util.Map;

public interface GalleryService {
	//pageNum 에 해당하는 gallery 페이지 정보를 Map 에 담아서 리턴하는 메소드
	public Map<String, Object> selectPage(int pageNum);
}
