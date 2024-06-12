package co.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Control;
import co.yedam.service.BoardService;
import co.yedam.service.BoardServiceImpl;
import co.yedam.vo.BoardVO;

public class AddBoard implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");

		BoardVO bvo = new BoardVO();
		bvo.setContent(content);
		bvo.setWriter(writer);
		bvo.setTitle(title);

		BoardService svc = new BoardServiceImpl();
		// 등록이 성공하면 글목록으로 이동.
		// 처리중에 에러가 발생하면 등록화면으로 이동.
		try {
			if (svc.addBoard(bvo)) {
				resp.sendRedirect("boardList.do");
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "<b>등록처리 중 오류가 발생했습니다.<b>");
			req.getRequestDispatcher("WEB-INF/view/boardForm.jsp").forward(req, resp);

		}
	}

}
