package com.example.boot11.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot11.dto.FileDto;
import com.example.boot11.service.FileService;

@Controller
public class FileController {
	
	@Autowired FileService service;
	
	@GetMapping("/file/download")
	public  ResponseEntity<InputStreamResource> download(int num) throws UnsupportedEncodingException, FileNotFoundException {
		return service.download(num);
	}
	
	@GetMapping("/file/delete")
	public String delete(int num) {
		service.delete(num);		
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/uploadform")
	public String uploadForm() {
		return "file/uploadform";
	}
	
	@PostMapping("/file/upload")
	public String upload(FileDto dto) {
		service.insert(dto);
		return "file/upload";
	}
	// FileDto 에는 pageNum, condition, keyword 값이 담길수도 있다.
	// get 방식 파라미터값이 넘어오면 담긴다.
	@GetMapping("/file/list")
	public String list(Model model, FileDto dto) {
		//서비스 객체에 Model 의 참조값을 전달해서 파일 목록이 Model 객체에 담기도록 한다.
		service.getList(model, dto);
		return "file/list";
	}
	
}

