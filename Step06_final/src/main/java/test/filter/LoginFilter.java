package test.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
 * 특정 경로 요청에 대해서 클라이언트가 로그인을 한 클라이언트인지 확인 작업을 하는 필터 정의하기
 * 
 * 1. Filter 인터페이스 구현
 * 2. 추상메소드 오버라이드
 * 3. @wevFilter 어노테이션을 이용해서 필터 맵핑
 */
//game, stop 하위의 모든 요청들은 필터를 거친다.
@WebFilter({"/user/private/*","/user/file/private/*","/shop/*","/cafe/private/*"})
public class LoginFilter implements Filter{
	@Override
	public void destroy() {	
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//session 객체 에러 해결하기
		//원래 type 으로 casting 하기
		HttpServletRequest req = (HttpServletRequest)request;
		//자식 type 을 이용해서 HttpSession 객체의 참조값을 얻어낸다.
		HttpSession session = req.getSession();
	
   		//session 영역에 id 라는 키값으로 저장된 값이 있는지 확인한다(로그인된 사용자인지 확인)
   		String id = (String)session.getAttribute("id");
    	//만일 없다면(로그인된 사용자가 아니라면)
    	if(id==null){
    		/*
			 *  로그인 페이지로 강제 리다일렉트 됬다면 
			 *  로그인 성공후에 원래 가려던 목적지로 다시 보내야 하고
			 *  GET 방식 전송 파라미터가 있다면 파라미터 정보도 같이 가지고 갈수 있도록 해야한다.
			 */
			//원래 가려던 url 정보 읽어오기
			String url=req.getRequestURI();
			//GET 방식 전송 파라미터를 query 문자열로 읽어오기 ( a=xxx&b=xxx&c=xxx )
			String query=req.getQueryString();
			//특수 문자는 인코딩을 해야한다.
			String encodedUrl=null;
			if(query==null) {//전송 파라미터가 없다면 
				encodedUrl=URLEncoder.encode(url);
			}else {
				// 원래 목적지가 /test/xxx.jsp 라고 가정하면 아래와 같은 형식의 문자열을 만든다.
				// "/test/xxx.jsp?a=xxx&b=xxx ..."
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}
    		//로그인 페이지로 리다일렉트 시키기
    		String cPath = req.getContextPath();
    		HttpServletResponse res = (HttpServletResponse)response;
    		res.sendRedirect(cPath+"/user/loginform.jsp?url="+encodedUrl);
    	}else {//로그인을 한 사용자라면
    		//관여하지 않고 요청의 흐름을 이어간다(
    		chain.doFilter(request, response);
    	}
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
