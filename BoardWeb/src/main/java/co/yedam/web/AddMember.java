package co.yedam.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Control;
import co.yedam.service.BoardService;
import co.yedam.service.BoardServiceImpl;
import co.yedam.vo.MemberVO;

public class AddMember implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String nm = req.getParameter("name");

		MemberVO mvo = new MemberVO();
		mvo.setUserId(id);
		mvo.setUserPw(pw);
		mvo.setUserName(nm);

		BoardService svc = new BoardServiceImpl();
		Map<String, String> result = svc.addMember(mvo);

		if (result.get("retCode").equals("OK")) {
			resp.sendRedirect("main.do");

		} else {
			req.setAttribute("message", result.get("message"));
			req.getRequestDispatcher("member/addMemberForm.tiles")//
					.forward(req, resp);
		}

	}

}
