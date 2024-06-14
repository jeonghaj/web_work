package test.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.member.dto.MemberDto;
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DB 에서 읽어온 회원 목록이라고 가정
		MemberDto mem1 = new MemberDto(1,"김구라","노량진");
		MemberDto mem2 = new MemberDto(2,"해골","행신동");
		MemberDto mem3 = new MemberDto(3,"원숭이","동물원");
		List<MemberDto> list = new ArrayList<MemberDto>();
		list.add(mem1);
		list.add(mem2);
		list.add(mem3);
		
		//webapp/member/list.jsp 페이지에 회원목록을 table 요소를 이용해서 출력하도록 해 보세요.
		req.setAttribute("list", list);
		RequestDispatcher rd = req.getRequestDispatcher("/member/list.jsp");
		rd.forward(req, resp);
		
	}
}
