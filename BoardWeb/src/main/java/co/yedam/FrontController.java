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
import co.yedam.web.AddBoard;
import co.yedam.web.AddForm;
import co.yedam.web.AddMember;
import co.yedam.web.AddStudent;
import co.yedam.web.BoardForm;
import co.yedam.web.BoardList;
import co.yedam.web.GetBoard;
import co.yedam.web.LoginControl;
import co.yedam.web.LoginForm;
import co.yedam.web.LogoutControl;
import co.yedam.web.MainControl;
import co.yedam.web.MemberList;
import co.yedam.web.ModifyBoard;
import co.yedam.web.ModifyForm;
import co.yedam.web.ProductControl;
import co.yedam.web.ScriptForm;
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
		// 상세화면.
		map.put("/getBoard.do", new GetBoard());
		// 글등록화면.
		map.put("/addForm.do", new BoardForm());
		// 글등록 후 목록이동.
		map.put("/addBoard.do", new AddBoard());
		// 수정화면
		map.put("/modifyForm.do", new ModifyForm());
		// 수정처리
		map.put("/modifyBoard.do", new ModifyBoard());
		// 로그인화면
		map.put("/loginForm.do", new LoginForm());
		// 로그인기능
		map.put("/login.do", new LoginControl());
		// 로그아웃
		map.put("/logout.do", new LogoutControl());
		// 회원목록(관리자템플릿)
		map.put("/memberList.do", new MemberList());
		// 회원등록.
		map.put("/addMemberForm.do", new AddForm());
		map.put("/addMember.do", new AddMember());

		// 자바스크립트 연습용 페이지.
		map.put("/script.do", new ScriptForm());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) //
			throws ServletException, IOException {

		String uri = req.getRequestURI(); // http://localhost/BoardWeb/main.do
		String context = req.getContextPath(); // /BoardWeb => project name.
		String page = uri.substring(context.length());
		System.out.println("page: " + page);

		Control result = map.get(page);
		result.exec(req, resp);

	}
}
