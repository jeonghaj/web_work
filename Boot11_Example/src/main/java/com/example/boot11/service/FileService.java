package com.example.boot11.service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.example.boot11.dto.FileDto;

public interface FileService {
	public void getList(Model model);
//	public List<FileDto> getList();
	public  ResponseEntity<InputStreamResource> download(int num) throws UnsupportedEncodingException, FileNotFoundException;
	
	public void insert(FileDto dto);
	public void delete(int num);
	
}
