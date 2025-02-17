package com.example.boot12.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	//숫자로된 아이디는 PK
	private int id;
	//사용자명은 중복된 데이터가 들어가지 않도록 UNIQUE KEY 를 설정해야한다.
	private String userName;
	private String password;
	private String newPassword;
	private String email;
	//Authority 정보를 저장할 칼럼 ADMIN | STAFF | USER 형식이다.
	private String role;
	private String profile;
	private String regdate;
	//프로필 이미지 파일 업로드 처리를 하기위한 필드
	// <input type="file" name="image" > 임으로 필드명이 image 이다
	private MultipartFile image; 
}
