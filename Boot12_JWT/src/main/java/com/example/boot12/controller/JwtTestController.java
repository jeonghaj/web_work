package com.example.boot12.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot12.dto.UserDto;
import com.example.boot12.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class JwtTestController {
	//Jwt 토큰 유틸
	@Autowired 
	private JwtUtil jwtUtil;	
	//jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	//쿠키 유지시간
	@Value("${jwt.cookie.expiration}")
	private int cookieExpiration;
	
	// React js 를 테스트 하기 위한 코딩
	@Autowired AuthenticationManager authManager;
	

	@ResponseBody
	@PostMapping("/api/auth")
	public String auth(@RequestBody UserDto dto ) throws Exception {
		try {
			//입력한 username 과 password 를 인증토큰 객체에 담아서 
			UsernamePasswordAuthenticationToken authToken=
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());	
			//인증 메니저 객체를 이용해서 인증을 진행한다 
			authManager.authenticate(authToken);
		}catch(Exception e) {
			//예외가 발생하면 인증실패(아이디 혹은 비밀번호 틀림 등등...)
			e.printStackTrace();
			throw new Exception("아이디 혹은 비밀번호가 틀려요!");
		}
		//예외가 발생하지 않고 여기까지 실행 된다면 인증을 통과 한 것이다. 토큰을 발급해서 응답한다.
		String token=jwtUtil.generateToken(dto.getUserName());
		
		return "Bearer+"+token;
	}
	
	@ResponseBody
	@GetMapping("/api/names") //토큰이 있어야지만 요청이 가능
	public List<String> names(){
		
		return Arrays.asList("김구라","해골","원숭이");
	}
	
	@ResponseBody
	@PostMapping("/api/test/login")
	public String apiLogin() {
		//토큰을 발급해서 쿠키로 
		String jwtToken=jwtUtil.generateToken("kimgura");
		return "Bearer+"+jwtToken;
	}
	
	@GetMapping("/test/login")
	public String login(HttpServletResponse response) {
		//토큰을 발급해서 쿠키로 
		String jwtToken=jwtUtil.generateToken("kimgura");
		// JWT를 쿠키에 담아 응답 (관례상 jwt 토큰 앞에 Bearer 라는 접두어를 붙인다)
		// cookie 에서는 공백을 인식하지못해 "+" 기호로 표현한다.
        Cookie cookie = new Cookie(jwtName, "Bearer+" + jwtToken);
        cookie.setMaxAge(cookieExpiration); // 쿠키 유지 시간 초 단위로 설정
        cookie.setHttpOnly(true); //웹브라우저에서 JavaScript에서 접근 불가 하도록 설정 
        cookie.setPath("/"); // 모든 경로에서 쿠키를 사용할수 있도록 설정 
        response.addCookie(cookie);
        
		return "redirect:/";
	}
	
	@GetMapping("/test/logout")
	public String logout(HttpServletResponse response) {
		// jwt 를 저장하고 있는 쿠키를 삭제하면 로그아웃과 동일한 효과를 낸다 
		Cookie cook=new Cookie(jwtName, null);
		//쿠키를 삭제하기 위해 setMaxAge(0)
		cook.setMaxAge(0);
		cook.setPath("/");
		response.addCookie(cook);
		
		return "redirect:/";
	}
}















