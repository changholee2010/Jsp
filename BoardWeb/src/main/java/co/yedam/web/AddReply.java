package co.yedam.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.common.Control;
import co.yedam.service.ReplyService;
import co.yedam.service.ReplyServiceImpl;
import co.yedam.vo.ReplyVO;

public class AddReply implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");

		String bno = req.getParameter("bno");
		String replyer = req.getParameter("replyer");
		String reply = req.getParameter("reply");

		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(Integer.parseInt(bno));
		rvo.setReply(reply);
		rvo.setReplyer(replyer);
		System.out.println(rvo);

		Map<String, Object> map = new HashMap<>();
		Gson gson = new GsonBuilder().create();

		ReplyService svc = new ReplyServiceImpl();

		try {
			if (svc.registerReply(rvo)) {
				rvo.setReplyDate(new Date());
				System.out.println(rvo);

				map.put("retCode", "OK");
				map.put("retVal", rvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("retCode", "NG");
			map.put("retVal", "처리중 오류가 발생했습니다.");
		}
		// 웹브라우즈 출력.
		resp.getWriter().print(gson.toJson(map));
	}

}
