package com.example.boot07.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GalleryController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping("/gallery/uploadform")
	public String uploadform() {
		return "gallery/uploadform";
	}
	
	// <input type="file" name="image"> 의 name 속성의 value 가 image 이기 때문에
	// MultipartFile image 로 매개 변수를 선언하면 된다.
	@PostMapping("/gallery/upload")
	public String upload(MultipartFile image, Model m) {
		//저장할 파일의 이름 겹치지 않는 유일한 문자열로 얻어내기
				String saveFileName=UUID.randomUUID().toString();
				//저장할 파일의 전체 경로 구성하기
				String filePath=fileLocation+File.separator+saveFileName;
				try {
					//업로드된 파일을 이동시킬 목적지 File 객체
					File f=new File(filePath);
					//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
					image.transferTo(f);
				}catch(Exception e) {
					e.printStackTrace();
				}
				m.addAttribute("saveFileName", saveFileName);
		return "gallery/upload";
	}
}
