package com.example.boot06.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot06.interceptor.LoginInterceptor;

//설정과 관련된 bean 
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInter;
	
	// 인터셉터를 레지스트리에 등록할때 호출되는 메소드를 오버라이드 한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//로그인 입터센터를 거치지 않을 요청 목록
		String[] whiteList= {"/sub/play", "/cafe/list", "/cafe/detail"};
		
		//동작을 원하는 인터센터를 registry 객체를 이용해서 등록을 한다.
		registry.addInterceptor(loginInter)
		.addPathPatterns("/sub/*", "/cafe/*") // 인터센터가 동작할 요청 pattern
		.excludePathPatterns(whiteList); // 인터셉터가 동작하지않는(예외) pattern
	}
}
