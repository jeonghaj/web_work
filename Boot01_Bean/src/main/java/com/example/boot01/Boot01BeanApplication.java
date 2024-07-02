package com.example.boot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//이 클래스가 존재하는 패키지 (com.example.boot01) 또는 하위 패키지를 모두 scan 해서
//spring 이 관리할 객체를 생성하도록 한다  (@SpringBootApplication 어노테이션의 기능)
@SpringBootApplication
public class Boot01BeanApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Boot01BeanApplication.class, args);
		
		// Spring bean container 에서 HomeService type 을 찾아서 리턴 받는다.
		// HomeService service = ctx.getBean(HomeService.class);
		
//		service.clean("원숭이");
//		service.wash("주뎅이");
//		service.hole("바닥");
	}

}
