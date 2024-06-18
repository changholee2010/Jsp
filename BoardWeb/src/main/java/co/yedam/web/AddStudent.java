package co.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Control;
import co.yedam.service.StudentService;
import co.yedam.service.StudentServiceImpl;
import co.yedam.vo.Student;

public class AddStudent implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String no = req.getParameter("sno");
		String name = req.getParameter("sname");
		String phone = req.getParameter("phone");
		String btype = req.getParameter("btype");

		StudentService svc = new StudentServiceImpl();

		Student student = new Student();
		student.setStdNo(no);
		student.setStdName(name);
		student.setPhone(phone);
		student.setBldType(btype);
		try {
			if (svc.addStudent(student)) {
//			sqlSession.commit(); // 커밋.
				System.out.println("정상등록...");
				resp.sendRedirect("main.do");
			}
		} catch (Exception e) {

			req.setAttribute("message", "처리중 오류가 발생.");
			req.getRequestDispatcher("WEB-INF/view/studentForm.jsp").forward(req, resp);
		}

	}

}
