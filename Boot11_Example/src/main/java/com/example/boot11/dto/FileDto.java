package com.example.boot11.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 // Mapper xml 에서 FilDto type 을 간단히 줄여서 쓸 수 있도록 별칠 부여
@Alias("fileDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {
	private int num;
	private String writer;
	private String title;
	private String orgFileName;
	private String saveFileName;
	private long fileSize;
	private String regdate;
	
	private int pageNum=1;
	private int startRowNum;
	private int endRowNum;
	//파일 업로드 처리를 하기 위한 필드
	private MultipartFile myFile;
	//검색 키워드 관련
	private String condition="";// 검색조건이 없는경우 null 이 출력되는걸 방지하기위해 빈 문자열을 기본값으로 설정
	private String keyword="";// 검색조건이 없는경우 null 이 출력되는걸 방지하기위해 빈 문자열을 기본값으로 설정
}
