package com.example.boot11.repository;

import java.util.List;

import com.example.boot11.dto.GalleryDto;

public interface GalleryDao {
	public void upload(GalleryDto dto);
	public List<GalleryDto> getList();
	public GalleryDto getData(int num);
}
