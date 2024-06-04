package test.servletpac;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*	<form action="send" method="get">
		<input type="text" name="msg" placeholder="할말 입력..." />
		<button type="submit">전송</button>
	</form>
	
	 위의 폼을 제출하면(전송 버튼을 누르면  = submit)
	 
 	 get 방식 / send 요청이 된다.
 	 요청이 되면서 msg 라는 파라미터 명으로 input 요소에 입력한 문자열이 같이 전송된다.
 	 같이 전송되는 정보를 요청 파라미터라고 한다.
 	 get 방식 요청 파라미터는 주소창으로 확인 가능하다.
 	 예를들어 input 요소에 hello 를 입력하고 전송하면 주소창에
 	 /send?msg=hello 가 나타난다.
 	 
 	 해당 요청을 처리하는 서블릿에서는 HttpServeltRequest 의 메소드를 이용해서 요청 파라미터를 추출할 수 있다.
 */
@WebServlet("/send")
public class SendServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//msg 라는 파라미터 명으로 전달되는 요청 파라미터 문자열을 추출하기
		String msg = req.getParameter("msg");
		System.out.println("msg:"+msg);
		//요청에대한 응답
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
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("메세지 잘 받았음");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
