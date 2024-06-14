package co.yedam.common;

import org.apache.ibatis.session.SqlSession;

import co.yedam.mapper.BoardMapper;
import co.yedam.service.BoardService;
import co.yedam.service.BoardServiceImpl;

public class AppTest {
	public static void main(String[] args) {
		SqlSession sqlSession //
				= DataSource.getInstance().openSession(true);
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

		SearchVO search = new SearchVO(2, "", "");

		mapper.boardListPaging(search)//
				.forEach(bvo -> System.out.println(bvo));

	}
}
