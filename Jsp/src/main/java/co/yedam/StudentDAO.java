package co.yedam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends DAO {
	// 등록,삭제,수정,목록 기능 구현.

	boolean updateStudent(Student std) {
		getConnection();
		String sql = "update tbl_student\r\n"//
				+ "set    phone = ?\r\n"//
				+ "where std_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, std.getPhone());
			psmt.setString(2, std.getStdNo());

			if (psmt.executeUpdate() == 1) { // 처리된 건수 == 1
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn(); // 자원 해제.
		}
		return false;
	} // end of updateStudent()

	boolean deleteStudent(String stdNo) {
		getConnection();
		String sql = "delete from tbl_student where std_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, stdNo);
			if (psmt.executeUpdate() == 1) { // 처리된 건수 == 1
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn(); // 자원 해제.
		}
		return false;
	} // end of deleteStudent()

	boolean insertStudent(Student std) {
		getConnection();
		String sql = "insert into tbl_student(std_no, std_name, phone, bld_type) values(?,?,?,?) ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, std.getStdNo());
			psmt.setString(2, std.getStdName());
			psmt.setString(3, std.getPhone());
			psmt.setString(4, std.getBldType());
			if (psmt.executeUpdate() == 1) { // 처리된 건수 == 1
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn(); // 자원 해제.
		}
		return false;
	} // end of insertStudent();

	List<Student> studentList() {
		getConnection(); // Connection객체(세션)
		String sql = "select * from tbl_student order by std_no";
		List<Student> students = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			// 조회된 결과목록을 List<Student>에 담아서 반환.
			while (rs.next()) {
				Student std = new Student();
				std.setStdNo(rs.getString("std_no"));
				std.setStdName(rs.getString("std_name"));
				std.setPhone(rs.getString("phone"));
				std.setBldType(rs.getString("bld_type"));
				std.setCreateDate(rs.getDate("create_date"));

				students.add(std); // 데이터베이스의 조회값 하나를 List에 담아줌.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn(); // 자원 해제.
		}
		return students;
	} // end of studentList()
}
