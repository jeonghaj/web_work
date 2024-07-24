package com.example.boot11.dto;

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
	private String saveFileName; // 업로드된 이미지의 저장된 이름
	private String regdate;
	private long fileSize;
	MultipartFile myFile;
}
