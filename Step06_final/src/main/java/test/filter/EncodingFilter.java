package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * post 방식 전송될 때 인코딩 설정을 해주지 않으면 한글이 꺠지기 때문에 필터에세
 * 일괄적으로 인코딩 설정해주기
 */

@WebFilter("/*") //모든 요청에 대한 필터링
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1. 만일 인코딩 설정이 되지 않았다면
		if(request.getCharacterEncoding()==null) {
			//POST 방식 전송했을때 한글 깨짐 방지
			request.setCharacterEncoding("utf-8");
		}
		//2. 요청의 흐름 이어가기
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
