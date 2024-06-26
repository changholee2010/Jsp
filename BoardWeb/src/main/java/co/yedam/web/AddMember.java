package co.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.common.Control;
import co.yedam.service.BoardService;
import co.yedam.service.BoardServiceImpl;
import co.yedam.vo.MemberVO;

public class AddMember implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 파일첨부일 경우에는 multipart 요청을 처리.
		// Multipart요청 (1.요청정보 2.저장위치 3.최대크기 4.인코딩 5.리네임정책)
		String savePath = req.getServletContext().getRealPath("images");
		int maxSize = 1024 * 1024 * 5;
		String encoding = "utf-8";

		MultipartRequest mr = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

		String id = mr.getParameter("id");
		String pw = mr.getParameter("pw");
		String nm = mr.getParameter("name");
		String img = mr.getFilesystemName("myImage");

		MemberVO mvo = new MemberVO();
		mvo.setUserId(id);
		mvo.setUserPw(pw);
		mvo.setUserName(nm);
		mvo.setImage(img);

		BoardService svc = new BoardServiceImpl();
		Map<String, Object> resultMap = new HashMap<>();
		Gson gson = new GsonBuilder().create();

		try {
			if (svc.addMemberImage(mvo)) {
				if (req.getMethod().equals("POST")) { // form태그의 post요청처리.
					resp.sendRedirect("memberList.do");

				} else if (req.getMethod().equals("PUT")) { // fetch의 PUT요청처리.
					// {"retCode": "OK"}
					mvo.setResponsibility("User");
					resultMap.put("retCode", "OK");
					resultMap.put("retVal", mvo);
					resp.getWriter().print(gson.toJson(resultMap));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (req.getMethod().equals("PUT")) {
				// {"retCode": "NG"}
				resultMap.put("retCode", "NG");
				resp.getWriter().print(gson.toJson(resultMap));
			}
		}

	}

}
