package co.yedam.common;

import org.apache.ibatis.session.SqlSession;

import co.yedam.mapper.ReplyMapper;
import co.yedam.vo.ReplyVO;

public class AppTest {
	public static void main(String[] args) {
		SqlSession sqlSession //
				= DataSource.getInstance().openSession(true);
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);

		// interface에 구현해 메소드가 하나만 있는 인터페이스: 함수형인터페이스.
		mapper.selectListPaging(201, 5)//
				.forEach(reply -> System.out.println(reply));
	}
}
