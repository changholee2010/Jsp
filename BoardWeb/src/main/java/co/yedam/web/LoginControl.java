package co.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.common.Control;
import co.yedam.service.BoardService;
import co.yedam.service.BoardServiceImpl;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		BoardService svc = new BoardServiceImpl();

		if (svc.checkMember(id, pw)) {
			// 로그인.
			HttpSession session = req.getSession();
			session.setAttribute("logId", id);

			resp.sendRedirect("main.do");
		} else {
			resp.sendRedirect("loginForm.do");
		}

	}

}
