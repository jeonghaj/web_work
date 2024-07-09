package com.example.boot08.dto;

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
	private String email;
	//Authority 정보를 저장할 칼럼 ROLE_XXX 형식이다.
	private String role;
	
}
