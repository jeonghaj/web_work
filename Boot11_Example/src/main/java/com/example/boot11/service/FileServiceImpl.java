package com.example.boot11.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot11.dto.FileDto;
import com.example.boot11.repository.FileDao;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired private FileDao dao;
	//업로드된 이미지를 저장할 파일시스템 상의 위치 
	@Value("${file.location}")
	private String fileLocation;
	
	@Override
	public void upload(FileDto dto) {
		
		MultipartFile image = dto.getImage();
		dto.setOrgFileName(image.getOriginalFilename()); 
		//만일 선택한 프로필 이미지가 있다면 
		long fileSize = image.getSize();
		if(fileSize != 0) {
			dto.setFileSize(fileSize);
			//파일을 원하는 위치로 이동시켜 놓고 
			String saveFileName=UUID.randomUUID().toString();
			//저장할 파일의 전체 경로 구성하기
			String filePath=fileLocation+File.separator+saveFileName;
			try {
				//업로드된 파일을 이동시킬 목적지 File 객체
				File f=new File(filePath);
				//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
				dto.getImage().transferTo(f);
			}catch(Exception e) {
				e.printStackTrace();
			}
			dto.setSaveFileName(saveFileName);
		}
		//로그인된 userName 도 dto 에 담아준다 
		String writer = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		
		//dao 를 이용해서 수정반영한다
		dao.upload(dto);	
	}

}
