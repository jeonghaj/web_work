package com.example.boot13.service;

import org.springframework.ui.Model;

import com.example.boot13.dto.CookDto;

public interface CookService {
	public void getList(Model model);
	public void insert(CookDto dto);
	public void delete(Long num);
	public void getData(Long num, Model model);
	public void update(CookDto dto);
}
