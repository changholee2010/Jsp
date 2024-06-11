package co.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Control;
import co.yedam.web.AddStudent;
import co.yedam.web.BoardList;
import co.yedam.web.MainControl;
import co.yedam.web.ProductControl;
import co.yedam.web.StudentForm;

// front -> 요청url(*.do) - 실행컨트롤 매칭.
// main.do -> FrontController -> /WEB-INF/public/main.jsp
// 객체생성 -> init -> service -> destroy
public class FrontController extends HttpServlet {
	private Map<String, Control> map; // key: url, value: control

	public FrontController() {
		map = new HashMap<>();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainControl());
		map.put("/product.do", new ProductControl());
		// map.put("/board.do", "게시판페이지입니다");
		// 학생등록화면 studentForm.do
		map.put("/studentForm.do", new StudentForm()); // 등록화면.
		map.put("/addStudent.do", new AddStudent()); // 정보db에 저장.

		// 게시글목록.
		map.put("/boardList.do", new BoardList());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) //
			throws ServletException, IOException {

		String uri = req.getRequestURI(); // http://localhost/BoardWeb/main.do
//		System.out.println("uri: " + uri); // /BoardWeb/main.do
		String context = req.getContextPath(); // /BoardWeb => project name.
//		System.out.println("context: " + context);
		String page = uri.substring(context.length());
//		System.out.println("page: " + page);

		Control result = map.get(page);
		result.exec(req, resp);

	}
}
