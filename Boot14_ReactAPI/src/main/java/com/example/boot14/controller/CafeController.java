package com.example.boot14.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot14.dto.CafeDto;
import com.example.boot14.service.CafeService;

@RestController
public class CafeController {
	
	@Autowired private CafeService service;
	/*
	 * 	클라이언트가 json 문자열을 요청 파라미터로 전달하기 때문에
	 * 파라미터를 추출할때는 @RequestBody 어노테이션이 필요하다.
	 */
	
	@PostMapping("/cafe")
	public Map<String, Object> insert(@RequestBody CafeDto dto){
		
		service.saveContent(dto);
		
		return Map.of("isSuccess", true);
	}
}
