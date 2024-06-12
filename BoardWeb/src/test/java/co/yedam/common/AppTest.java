package co.yedam.common;

import co.yedam.service.BoardService;
import co.yedam.service.BoardServiceImpl;

public class AppTest {
	public static void main(String[] args) {
		BoardService svc = new BoardServiceImpl();

		System.out.println(svc.getBoard(100));

	}
}
