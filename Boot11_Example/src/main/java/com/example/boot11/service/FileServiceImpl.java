package com.example.boot11.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot11.dto.FileDto;
import com.example.boot11.exception.UserNameException;
import com.example.boot11.repository.FileDao;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao dao;
	// 업로드된 이미지를 저장할 파일시스템 상의 위치
	@Value("${file.location}")
	private String fileLocation;
	//한 페이지에 글을 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지 UI를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;

//	@Override
//	public List<FileDto> getList() {
//		return dao.getList();
//	}
	@Override
	public void getList(Model model, FileDto dto) {
		// pageNum 에 해당하는 글정보를 select 에서 Model 객체에 담는 작업을 하면 된다.
		
		int pageNum=dto.getPageNum();
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int totalRow=dao.getCount(dto);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		//위에서 계산된 startRowNum 과 endRowNum 을 dto 담고
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//dao 를 이용해서 파일 목록을 얻어온다음
		List<FileDto> list=dao.getList(dto);
		//Model 객체에 template 페이지에서 필요한 정보를 담아준다.
		model.addAttribute("list", list);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("pageNum", pageNum);
		
		model.addAttribute("dto",dto);
		model.addAttribute("totalRow", totalRow);
	}
	
	@Override
	public void insert(FileDto dto) {
		//파일 업로드 처리를 위함 객체의 참조값 얻어오기(업로드된 파일에 대한 정보를 얻어낼 객체)
		MultipartFile myFile = dto.getMyFile();
		// 원본 파일명
		dto.setOrgFileName(myFile.getOriginalFilename());
		// 파일의 크기
		long fileSize = myFile.getSize();
		if (fileSize != 0) {
			dto.setFileSize(fileSize);
			// 파일을 원하는 위치로 이동시켜 놓고
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
		// 로그인된 userName 도 dto 에 담아준다
		String writer = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);

		// dao 를 이용해서 수정반영한다
		dao.insert(dto);
	}
//	@Override
//	public void saveFile(FileDto dto) {
//		//파일 업로드 처리를 위한 객체의 참조값 얻어오기(업로드된 파일에 대한 정보를 얻어낼 객체)
//		MultipartFile myFile=dto.getMyFile();
//		//원본 파일명
//		String orgFileName=myFile.getOriginalFilename();
//		//파일의 크기
//		long fileSize=myFile.getSize();
//		//저장할 파일명을 하나 얻어낸다. 
//		String saveFileName=UUID.randomUUID().toString();
//		//저장할 파일의 상세 경로
//		String filePath = fileLocation+File.separator+saveFileName;
//		try {
//			//File 객체 생성 
//			File f=new File(filePath);
//			//파일을 원하는 곳에 저장하기 
//			myFile.transferTo(f);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		//DB 에 업로드된 파일에 대한 정보를 저장한다.
//		
//		//로그인된 userName 이 글 작성자가 된다. 
//		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
//		dto.setWriter(userName);
//		dto.setOrgFileName(orgFileName);
//		dto.setSaveFileName(saveFileName);
//		dto.setFileSize(fileSize);
//		
//		dao.insert(dto);
//	}

	@Override
	public ResponseEntity<InputStreamResource> download(int num) throws UnsupportedEncodingException, FileNotFoundException{
		FileDto dto = dao.getData(num);
		// 다운로드 시켜줄 원본 파일명
		String encodedName = URLEncoder.encode(dto.getOrgFileName(), "utf-8");
		// 파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
		encodedName = encodedName.replaceAll("\\+", " ");
		// 응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
		HttpHeaders headers = new HttpHeaders();
		// 파일을 다운로드 시켜 주겠다는 정보
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
		// 파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedName);
		// 파일의 크기 정보도 담아준다.
		headers.setContentLength(dto.getFileSize());

		// 읽어들일 파일의 경로 구성
		String saveFileName = dto.getSaveFileName();
		String filePath = fileLocation + File.separator + saveFileName;
		// 파일에서 읽어들일 스트림 객체
		InputStream is = new FileInputStream(filePath);
		// InputStreamResource 객체의 참조값 얻어내기
		InputStreamResource isr = new InputStreamResource(is);
		// ResponseEntity 객체에 응답 헤더 정보와 응답 body 를 넣어서
		ResponseEntity<InputStreamResource> resEntity = ResponseEntity.ok().headers(headers).body(isr);
		// 리턴해주면 자동으로 다운로드가 된다.
		return resEntity;
		
//		//다운로드 해줄 파일의 정보를 DB 에서 읽어온다.
//		FileDto dto=dao.getData(num);
//		ResponseEntity<InputStreamResource> responseEn=null;
//		try {
//			//다운로드 시켜줄 원본 파일명
//			String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
//			//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
//			encodedName=encodedName.replaceAll("\\+"," ");
//			//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
//			HttpHeaders headers=new HttpHeaders();
//			//파일을 다운로드 시켜 주겠다는 정보
//			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
//			//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
//			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
//			//파일의 크기 정보도 담아준다.
//			headers.setContentLength(dto.getFileSize());
//			//읽어들일 파일의 경로 구성
//			String filePath=fileLocation + File.separator + dto.getSaveFileName();
//			//파일에서 읽어들일 스트림 객체 생성
//			InputStream is=new FileInputStream(filePath);
//			//InputStreamResource
//			InputStreamResource isr=new InputStreamResource(is);
//			//InputStremResource 객체를 얻어내서 지역변수에 담고
//			responseEn=ResponseEntity.ok().headers(headers).body(isr);
//		}catch(Exception e) {
//			//예외를 던지고 ExceptionController 에서 처리 할수 있다.
//			throw new RuntimeException("파일 다운로드 중에 예외가 발생했습니다");
//		}
//		//InputStreamResource 객체를 리턴해준다.
//		return responseEn;
	}
	
	@Override
	public void delete(int num) {
		//DB 에서 삭제할 파일의 정보를 읽어온다
		FileDto dto = dao.getData(num);
		String writer = dto.getWriter();
		//로그인 된 사용자와 파일의 소유자가 같은지 확인해서 다르면 Exception 발생시키기
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!userName.equals(writer)) {
			throw new UserNameException("작성자가 일치하지 않습니다.");
		};
		//파일 시스템에서 실제로 삭제하고
		String FilePath = fileLocation + File.separator + dto.getSaveFileName();
		File file = new File(FilePath);
		file.delete();
		//DB 에서도 삭제
		dao.delete(num);
	}

}
