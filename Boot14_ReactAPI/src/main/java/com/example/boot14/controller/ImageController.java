package com.example.boot14.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@ResponseBody
	@GetMapping(
		value = "/upload/images/{imageName}" , 
		// jpg, png, gif 이미지 데이터를 응답할수 있도록 produces 에 배열로 전달한다.
		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, 
			MediaType.IMAGE_GIF_VALUE}
	)
	public byte[] image(@PathVariable("imageName") String name) throws IOException{
		
		//읽어들일 파일의 절대 경로 
		String absolutePath=fileLocation + File.separator + name;
		// 파일에서 읽어들일 InputStream 
		InputStream is=new FileInputStream(absolutePath);
		// commons io 에 있는 IOUtils 클래스를 이용해서 이미지 파일에서 byte[] 을 얻어낸다 
		return IOUtils.toByteArray(is);
	}	
}
