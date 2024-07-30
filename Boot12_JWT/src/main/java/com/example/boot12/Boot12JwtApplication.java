package com.example.boot12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.boot12.util.JwtUtil;

import jakarta.annotation.PostConstruct;

@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Boot12JwtApplication {
	
	@Autowired private JwtUtil util;
	
	@PostConstruct
	public void jwtTest() {
		// "kimgura" 라는 id 로 토큰을 발급받아서 콘솔창에 출력해 보기
		String token = util.generateToken("kimgura");
		System.out.println("발급된 토큰 : " + "Bearer+" + token);
		//발급된 토큰을 복사해서 http://jwt.io 사이트에 들어가서 디코딩해볼 수 있다.
	}

	public static void main(String[] args) {
		SpringApplication.run(Boot12JwtApplication.class, args);
	}

}
