package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot11.dto.FileDto;
import com.example.boot11.service.FileService;

@Controller
public class FileController {
	
	@Autowired FileService service;
	
	
	@GetMapping("/file/list")
	public String list() {	
		return "file/list";
	}
	
	@GetMapping("/file/uploadform")
	public String uploadForm() {
		return "file/uploadform";
	}
	
	@PostMapping("/file/upload")
	public String upload(FileDto dto) {
		service.upload(dto);
		return "file/upload";
	}
	
}

