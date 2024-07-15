package com.example.boot11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	/*
	 *  매개변수에 전달되는 HttpSecurity 객체를 이용해서 우리의 프로젝트 상황에 맞는 설정을 기반으로 
	 *  만들어진 SecurityFilterChain 객체를 리턴해주어야 한다.
	 *  또한 SecurityFilterChain 객체도 스프링이 관리하는 Bean 이 되어야 한다  
	 */
	@Bean //메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		// 로그인요구를 예외로 할 페이지 ( home, 로그인이 필요없는 페이지, 자발적 로그인 폼)
		String [] whiteList= {"/","/user/loginform","/user/login_fail","/user/expired",
				"/user/signup_form","/user/signup", "/error","/upload/images/*",
				"/file/list", "/file/download"}; 
		//.csrf / .formLogin / .formLogout => Customizer type 반환
		httpSecurity
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(config ->
			config
				.requestMatchers(whiteList).permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/staff/**").hasAnyRole("ADMIN","STAFF")
				.anyRequest().authenticated() //모든 요청을 인증처리하겠다
		)
		.formLogin(config -> 
			config
				//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정
				.loginPage("/user/required_loginform")
				//로그인 처리를 할때 요청될 url 설정 (spring security 가 login 처리를 대신 해준다)
				.loginProcessingUrl("/user/login")
				//로그인 처리를 대신 하려면 어떤 파라미터명으로 username / password 가 넘어오는지 명시한다.
				.usernameParameter("userName") 
				.passwordParameter("password")
				.successHandler(new AuthSuccessHandler()) // 로그인 성공 핸들러 등록
				.failureForwardUrl("/user/login_fail")
				.permitAll() //위에 명시한 모든 요청경로를 로그인 없이 요청할 수 잇도록 설정
		)
		.logout(config->
			config
			.logoutUrl("/user/logout")//Spring Security 가 자동으로 로그아웃 처리 해줄 경로 설정
			.logoutSuccessUrl("/")//로그아웃 이후에 리다일렉트 시킬 경로 설정
			.permitAll()
		)
		.exceptionHandling(config->
			//403 forbidden 인 경우 forward 이동 시킬 경로 설정
			config.accessDeniedPage("/user/denied")
		)
		.sessionManagement(config->
			config
				.maximumSessions(1) //최대 허용 세션 갯수
				.expiredUrl("/user/expired") //허용 세션 갯수가 넘어서 로그인 해제된 경우 리다일렉트 이동 경로
				
		);
		
		return httpSecurity.build();
	}
	
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다. (spring security 가 사용한다)
	@Bean
	PasswordEncoder passwordEncoder() {
		//여기서 리턴해주는 객체도 bean 으로 된다.
		return new BCryptPasswordEncoder();
	}
	
}
