package com.example.boot12.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.CookieRequestCache;

import com.example.boot12.filter.JwtFilter;
import com.example.boot12.handler.AuthFailHandler;
import com.example.boot12.handler.AuthSuccessHandler;

import jakarta.servlet.http.Cookie;

@Configuration //설정 클래스라고 알려준다
@EnableWebSecurity //Security 를 설정하기 위한 어노테이션
public class SecurityConfig {
	//jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean //메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			AuthSuccessHandler successHandler, AuthFailHandler failHandler,
			CookieRequestCache cookCache) throws Exception{
		//화이트 리스트를 미리 배열에 넣어두기
		String[] whiteList= {"/", "/user/signup_form", "/user/signup", 
				"/user/loginform", "/user/login_fail", "/user/expired",
				"/test/login","/api/test/login","/upload/images/**"};
		
		//메소드의 매개변수에 HttpSecurity 의 참조값이 전달되는데 해당 객체를 이용해서 설정을 한다음
		httpSecurity
		.headers(header->
			//동일한 origin 에서 iframe 을 사용할수 있도록 설정(default 값은 사용불가)
			header.frameOptions(option->option.sameOrigin()) //SmartEditor 에서 필요함
		)
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(config -> 
			config
				.requestMatchers(whiteList).permitAll() //whiteList 요청은 로그인과 상관없이 모두 허용
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
				.anyRequest().authenticated() //위에 명시한 이외의 모든 요청은 로그인해야지 요청가능하게
		)
		.formLogin(config -> 
			config
				//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정 
				.loginPage("/user/required_loginform")
				//로그인 처리를 할때 요청될 url 설정 ( spring security 가 login 처리를 대신 해준다)
				.loginProcessingUrl("/user/login")
				//로그인 처리를 대신 하려면 어떤 파라미터명으로 username 과 password 가 넘어오는지 알려주기 
				.usernameParameter("userName") 
				.passwordParameter("password")
				.successHandler(successHandler)//로그인 성공 핸들러 등록
				.failureHandler(failHandler) //로그인 실패 핸들러 등록
				//.failureForwardUrl("/user/login_fail") //로그인 실패시 forward 될 url 설정 >> AuthFailHandler 로 처리
				.permitAll() //위에 명시한 모든 요청경로를 로그인 없이 요청할수 있도록 설정 
		)
		.logout(config ->
			config
				.logoutUrl("/user/logout")//Spring Security 가 자동으로 로그아웃 처리 해줄 경로 설정
				.logoutSuccessHandler((request, response, auth)->{
					Cookie cook=new Cookie(jwtName, null);
					//쿠키를 삭제하기 위해 setMaxAge(0)
					cook.setMaxAge(0);
					cook.setPath("/");
					response.addCookie(cook);
					//쿠키 삭제후에 최상위 경로로 리다일렉트 이동
					response.sendRedirect(request.getContextPath()+"/");
				})
				.permitAll()
		)
		.exceptionHandling(config ->
			//403 forbidden 인 경우 forward 이동 시킬 경로 설정 
			config.accessDeniedPage("/user/denied")
		)
		//세션을 사용하지 않겠다. ( 세션이 상태를 가지지 못하게 (STATELESS))
		.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		//토큰을 검사하는 필터를 security filter 가 동작하기 이전에 동작하도록 설정 한다.
		//모든 요청에 대해서 토큰 관련 필터링을 하겠다
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		//세션을 사용할수 없기때문에 쿠키케시를 사용하도록 설정한다. 
		.requestCache(config -> config.requestCache(cookCache));
		
		//설정된 정보대로 SecurityFilterChain 객체를 만들어서 반환한다 
		return httpSecurity.build();
	}
	
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
	@Bean
	PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}

	//쿠키 케시를 bean 으로 만든다. 
	@Bean
	CookieRequestCache getCookieRequestCache() {
		return new CookieRequestCache();
	}
}
