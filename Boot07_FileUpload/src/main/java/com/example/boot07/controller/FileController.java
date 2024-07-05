
package com.example.boot07.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot07.dto.FileDto;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class FileController {
	/*
	 *  custom.properties 파일에 있는 파일 업로드
	 *  경로를 읽어와서 필드에 담는다
	 */
	@Value("${file.location}")
	private String fileLocation;
	
	//파일 다운로드 요청처리
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(String orgFileName,
			String saveFileName, long fileSize) 
					throws UnsupportedEncodingException, FileNotFoundException{
		//원래는 DB 에서 읽어와야 하지만 지금은 다운로드해줄 파일의 정보가 요청 파라미터로 전달된다.
		
		//다운로드 시켜줄 원본 파일명
		String encodedName=URLEncoder.encode(orgFileName, "utf-8");
		//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
		encodedName=encodedName.replaceAll("\\+"," ");
		//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
		HttpHeaders headers=new HttpHeaders();
		//파일을 다운로드 시켜 주겠다는 정보
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
		//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
		//파일의 크기 정보도 담아준다.
		headers.setContentLength(fileSize);
		
		//읽어들일 파일의 경로 구성
		String filePath=fileLocation + File.separator + saveFileName;
		//파일에서 읽어들일 스트림 객체
		InputStream is=new FileInputStream(filePath);
		//InputStreamResource 객체의 참조값 얻어내기
		InputStreamResource isr=new InputStreamResource(is);
		//ResponseEntity 객체에 응답 헤더 정보와 응답 body 를 넣어서 
		ResponseEntity<InputStreamResource> resEntity=ResponseEntity.ok()
			.headers(headers)
			.body(isr);
		//리턴해주면 자동으로 다운로드가 된다. 
		return resEntity;
	}
	
	@GetMapping("/file/uploadform")
	public String uploadForm() {
		// 로그 레벨의 중요도
		// trace < debug < info < warn < error < fatal
		// spring boot 의 기본 로그 레벨은 info 이다
		// 따라서 debug 로그도 출력하게 하려면 application.properties 설정을 바꿔야한다.
		log.debug("여기가 실횅됨");
		
		return "file/uploadform";
	}
	
	/*
	 *  <input type="file" name="myFile" >  에 선택된 파일 데이터를 처리 하기 위해서는
	 *  
	 *  MultipartFile  myFile  로 매개변수를 선언하면 된다. 
	 */
	@PostMapping("/file/upload")
	public String upload(String title, MultipartFile myFile, Model m) {
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		//저장할 파일의 이름을 Universal Unique 한 ID 로 저장하기 위해
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기 
		String filePath=fileLocation + File.separator + saveFileName;
		try {
			//업로드된 파일을 이동시킬 목적지 File 객체 생성
			File dest=new File(filePath);
			//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
			myFile.transferTo(dest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//원래는 DB 에 저장해야 하지만 테스트를 위해  view page 에 전달하기 
		m.addAttribute("orgFileName", orgFileName);
		m.addAttribute("saveFileName", saveFileName);
		m.addAttribute("fileSize", fileSize);
		
		return "file/upload";
	}
	
	@PostMapping("/file/upload2")
	public String upload2(FileDto dto, Model m) {
		
		//MultipartFile 객체는 FileDto 객체로 부터 얻언내면 된다.
		MultipartFile myFile = dto.getMyFile();
		
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		//저장할 파일의 이름을 Universal Unique 한 ID 로 저장하기 위해
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기 
		String filePath=fileLocation + File.separator + saveFileName;
		try {
			//업로드된 파일을 이동시킬 목적지 File 객체 생성
			File dest=new File(filePath);
			//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
			myFile.transferTo(dest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//원래는 DB 에 저장해야 하지만 테스트를 위해  view page 에 전달하기 
		m.addAttribute("orgFileName", orgFileName);
		m.addAttribute("saveFileName", saveFileName);
		m.addAttribute("fileSize", fileSize);
		
		return "file/upload2";
	}
	
}
