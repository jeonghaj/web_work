package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//오늘의 운세를 얻어오는 비니지스 로직을 수행(DB 에서 읽어왔다고 가정)
		String fortune="동쪽으로 가면 귀인을 만나요";
		
		//오늘의 운세를 request 영역에 담고(HttpServletRequest 객체에 담는다)
		req.setAttribute("fortuneToday", fortune);
		
		//클라이언트에게 응답은 jsp 페이지에 위임한다 (forward 이동)
		RequestDispatcher rd = req.getRequestDispatcher("/test/fortune.jsp");
		rd.forward(req, resp);
	}
}
