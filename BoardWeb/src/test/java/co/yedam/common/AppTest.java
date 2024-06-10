package co.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.yedam.mapper.StudentMapper;
import co.yedam.vo.Student;

public class AppTest {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = DataSource.getInstance();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// interface - 구현객체.
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

		Student std = new Student();
		std.setStdNo("S0011");
		std.setStdName("김영식");
		std.setPhone("010-6789-6789");
		std.setBldType("O");

		// sqlSession.update("co.yedam.mapper.StudentMapper.updateStudent", std);
		mapper.deleteStudent(std.getStdNo());
		sqlSession.commit();
// 요기.....
		List<Student> list //
//				= sqlSession.selectList("co.yedam.mapper.StudentMapper.selectBlog");
				= mapper.selectBlog();
		for (Student std1 : list) {
			System.out.println(std1.toString());
		}
	}
}
