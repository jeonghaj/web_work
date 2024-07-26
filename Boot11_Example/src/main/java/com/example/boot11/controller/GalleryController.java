package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot11.dto.GalleryDto;
import com.example.boot11.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired private GalleryService service;
	
	@GetMapping("/gallery/delete")
	public String delete(int num) {
		service.deleteOne(num);
		return "redirect:/gallery/list";
	}
	
	@GetMapping("/gallery/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int pageNum) {
		//서비스에 Model 객체와 pageNum 을 전달해서 Model 에 pageNum 에 해당하는 글목록을 담는다
		//Model 에 담긴 내용을 view page(Thymeleaf 탬플릿 페이지) 에서 사용할 수 있다.
		service.selectPage(model, pageNum);
		return "gallery/list";
	}
	
	@PostMapping("/gallery/upload")
	public String upload(GalleryDto dto) {
		service.addToGallery(dto);
		return "redirect:/gallery/list";
	}
	
	@GetMapping("/gallery/upload_form")
	public String uploadForm() {
		return "gallery/upload_form";
	}
	
	@GetMapping("/gallery/detail")
	public String detail(Model model,int num) {
		//num 에는 자세히 보여줄 gallery 의 pk 가 들어있다.
		service.detail(model, num);
		return "gallery/detail";
	}
}
