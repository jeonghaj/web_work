package com.example.boot06.interceptor;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * - Interceptor 가 동작하는 조건
 * 
 * 1. bean 이 되어야 한다. (spring bean container 에 등록)
 * 2. Interceptor 레지스트리에 등록을 해야한다.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{
	//컨트롤러가 동작하기 이전(pre)에 이 메소드가 호출된다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션 객체의 참조값을 얻어와서 
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//만일 로그인을 하지 않았다면
		if(id == null) {
			//로그인 페이지로 리다일렉트 이동 시키고 false 를 리턴한다.

			//원래 가려던 url 정보 읽어오기
			String url=request.getRequestURI();
			//GET 방식 전송 파라미터를 query 문자열로 읽어오기 ( a=xxx&b=xxx&c=xxx )
			String query=request.getQueryString();
			//특수 문자는 인코딩을 해야한다.
			String encodedUrl=null;
			if(query==null) {//전송 파라미터가 없다면 
				encodedUrl=URLEncoder.encode(url);
			}else {
				// 원래 목적지가 /test/xxx.jsp 라고 가정하면 아래와 같은 형식의 문자열을 만든다.
				// "/test/xxx.jsp?a=xxx&b=xxx ..."
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}
			
			//3. 로그인을 하지 않았다면  /user/loginform 페이지로 리다일렉트 이동 시킨다. (HttpServletResponse)
			String cPath=request.getContextPath();
			response.sendRedirect(cPath+"/user/loginform?url="+encodedUrl);
			return false;
		}
		return true;
	}
}
