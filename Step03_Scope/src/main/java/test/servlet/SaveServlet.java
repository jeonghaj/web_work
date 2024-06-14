package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/test/save")
public class SaveServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//폼 전송되는 닉네임
		String nick = req.getParameter("nick");
		
		//session scope 에 저장하고
		
		//HttpSession 객체는 HttpServletRequest 객체의 메소드를 이용해서 얻어낼 수 있다.
		HttpSession session = req.getSession();
		session.setAttribute("nick", nick);
		//응답하기
		//응답 인코딩 설정
		resp.setCharacterEncoding("utf-8");
		//응답 컨텐트 설정
		resp.setContentType("text/html; charset=utf-8");
		//요청을 한 클라이언트에게 문자열을 출력할수 있는 객체의 참조값 얻어내기
		PrintWriter pw = resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>SaveServlet</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		pw.println("<p> <strong>"+nick+"</strong> 이라는 닉네임을 기억 하겠습니다</p>");
		pw.println("<a href='../index.jsp' >인덱스로</a>");
		
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
