package co.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import co.yedam.common.Control;
import co.yedam.common.DataSource;
import co.yedam.mapper.StudentMapper;
import co.yedam.vo.Student;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) //
			throws ServletException, IOException {
//		System.out.println("요청정보: " + req + ", 응답정보: " + resp);
		// WEB-INF/public/main.jsp
		// main.do 호출 -> main.jsp 페이지재지정.
		Student student = new Student();
		student.setStdNo("S0010");
		student.setStdName("홍길동");
		student.setPhone("010-1111-2222");

		SqlSession sqlSession //
				= DataSource.getInstance().openSession();
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		List<Student> list = mapper.selectBlog();

		req.setAttribute("student", student);
		req.setAttribute("studentList", list);

		req.getRequestDispatcher("WEB-INF/public/main.jsp")//
				.forward(req, resp);
	}

}
