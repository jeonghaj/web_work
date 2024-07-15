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
	
	private int startRowNum;
	private int endRowNum;
	//파일 업로드 처리를 하기 위한 필드
	private MultipartFile myFile;
}
