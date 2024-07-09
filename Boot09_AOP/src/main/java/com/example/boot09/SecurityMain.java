package com.example.boot09;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityMain {
	
	public static void main(String[] args) {
		//인코딩할 예정인 비밀번호
		String pwd = "1234";
		//비밀번호를 인코딩할 객체
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd = encoder.encode(pwd);
		System.out.println("인코딩된 비밀번호 : "+encodedPwd);
		
		System.out.print("비밀번호 입력 : ");
		//비밀번호 입력 받기
		String inputPwd = new Scanner(System.in).nextLine();
		//입력받은 비밀번호와 암호화된 비밀번호의 일치여부 비교
		boolean isValid = BCrypt.checkpw(inputPwd, encodedPwd);
		if(isValid) {
			System.out.println("일치합니다");
		}else {
			System.out.println("일치하지 않습니다");
		}
	}
	
}
