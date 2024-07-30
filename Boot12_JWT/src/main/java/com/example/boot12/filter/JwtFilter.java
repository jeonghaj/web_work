package com.example.boot12.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.boot12.service.CustomUserDetailsService;
import com.example.boot12.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//요청할때마다 한번 거치는 필터 만들기 (스프링 프레임워크 내에서 동작하는 필터)
// 클라이언트의 요청이 Controller 로 전해지기 전에 거쳐지는 Filter
@Component
public class JwtFilter extends OncePerRequestFilter{

	//jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	
	//JwtUtil 객체 주입 받기 
	@Autowired private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//1. 쿠키에서 JWT 토큰 추출
		Cookie[] cookies = request.getCookies();
        
        String jwtToken = "";
        if (cookies != null) {
        	// 반복문 돌면서
            for (Cookie cookie : cookies) {
            	// custom.properties 파일에 설정된 "jwtToken" 이라는 쿠키이름으로 저장된 value 가 있는지 확인
                if (jwtName.equals(cookie.getName())) {
                	// 있다면 그 value 값을 지역변수에 담기
                    jwtToken = cookie.getValue();
                    //반복문 탈출
                    break;
                }
            }
        }
        //만일 쿠키에서 추출된 토큰이 없다면
        if(jwtToken.equals("")) {
        	//Header 정보에서 얻어내기
        	String authHeader = request.getHeader("Authorization");
        	if(authHeader != null) {
        		jwtToken = authHeader;
        	}
        }
        
		//2. 토큰에서 userName 을 얻어내서 + extractUsername
		String userName = null;
		if(jwtToken.startsWith("Bearer+")) {
			// 앞에 "Bearer+" 를 제외한 순수 토큰 문자열 얻어내기
			// substring(7) 7글자 (Bearer+) 이후의 모든 문자열
			jwtToken = jwtToken.substring(7);
			userName = jwtUtil.extractUsername(jwtToken);
		}
		
		//userName 이 존재하고 Spring Security 에서 아직 인증을 받지 않은 상태라면
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			//3. DB 에서 UserDetails 객체를 얻어낸다음 
			UserDetails userDetails = service.loadUserByUsername(userName);
			
			//4. 토큰이 유효한 토큰인지 체크한다음 
			boolean isValid = jwtUtil.validateToken(jwtToken, userDetails);
			
			//5. 유효하다면 1회성 로그인(spring security 를 통과할 로그인) 을 시켜준다. 
			if(isValid) {
				
				//사용자가 제출한 사용자 이름과 비밀번호와 같은 인증 자격 증명을 저장
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null,
								userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//Security 컨텍스트 업데이트 (1회성 로그인)
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
		}
		System.out.println("JwtFilter 수행");
		// 다음 spring 필터 chain 진행하기
		filterChain.doFilter(request, response)	;		
		
	} //method

} //class









