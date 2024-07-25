package com.example.boot11.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot11.dto.GalleryDto;
import com.example.boot11.repository.GalleryDao;

@Service
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired private GalleryDao dao;
	@Value("${file.location}")
	private String fileLocation;
	
	@Override
	public void insert(GalleryDto dto) {
		
		MultipartFile myFile = dto.getMyFile();;
		// 파일의 크기
		long fileSize = myFile.getSize();
		if (fileSize != 0) {
			dto.setFileSize(fileSize);

			String saveFileName = UUID.randomUUID().toString();
			// 저장할 파일의 전체 경로 구성하기
			String filePath = fileLocation + File.separator + saveFileName;
			try {
				// 업로드된 파일을 이동시킬 목적지 File 객체
				File f = new File(filePath);
				// MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
				dto.getMyFile().transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setSaveFileName(saveFileName);
		}
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);		
		
		dao.upload(dto);		
	}

	@Override
	public void getList(Model model) {
		List<GalleryDto> list = dao.getList();
		
		model.addAttribute("list",list);
	}

	@Override
	public void detail(Model model, int num) {
		GalleryDto dto = dao.getData(num);
		model.addAttribute("dto",dto);
	}

}
