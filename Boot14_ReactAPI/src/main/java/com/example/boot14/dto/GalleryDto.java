package com.example.boot14.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("galleryDto")
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GalleryDto {
	private int num;
	private String writer;
	private String caption; //이미지에 대한 설명
	private String saveFileName; // 파일시스템에 저장된 이미지 파일명을 담을 필드
	private String regdate;
	
	private int startRowNum;
	private int endRowNum;
	
	// <input type="file" name="images" multiple>
	// 때문에 Multipart 배열 type 으로 필드를 선언하고 필드명도 images 로 변경
	private MultipartFile[] images; //여러개의 이미지 파일 업로드 처리를 위한 필드
	
	private int prevNum; //이전글의 글번호
	private int nextNum; //다음글의 글번호
}
