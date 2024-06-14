package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.member.dto.MemberDto;
@WebServlet("/member")
public class MemberServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//회원 한명의 정보를 DB 에서 읽어왔다고 가정
		MemberDto dto = new MemberDto();
		dto.setNum(1);
		dto.setName("김구라");
		dto.setAddr("노량진");
		
		//회원 한명의 정보를 담고 있는 MemberDto 객체를 request scope 에 담기
		req.setAttribute("dto", dto); // "dto" 라는 키값으로 MemberDto type 을 담았다.
		
		//jsp 페이지로 응답을 위임 시키기(jsp 페이지로 forward 이동)
		RequestDispatcher rd = req.getRequestDispatcher("/test/member.jsp");
		rd.forward(req, resp);
	}
}
