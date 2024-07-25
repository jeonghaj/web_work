package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot11.dto.GalleryDto;
import com.example.boot11.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired private GalleryService service;
	
	@GetMapping("/gallery/list")
	public String list(Model model) {
		service.getList(model);
		return "gallery/list";
	}
	
	@PostMapping("/gallery/upload")
	public String upload(GalleryDto dto) {
		service.insert(dto);
		return "gallery/upload";
	}
	
	@GetMapping("/gallery/upload_form")
	public String uploadForm() {
		return "gallery/upload_form";
	}
	
	@GetMapping("/gallery/detail")
	public String detail(Model model,int num) {
		service.detail(model, num);
		return "gallery/detail";
	}
}
