package com.example.boot11.service;

import org.springframework.ui.Model;

import com.example.boot11.dto.GalleryDto;

public interface GalleryService {
	public void insert(GalleryDto dto);
	public void getList(Model model);
}
