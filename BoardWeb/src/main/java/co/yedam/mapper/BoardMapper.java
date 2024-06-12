package co.yedam.mapper;

import java.util.List;

import co.yedam.vo.BoardVO;

/*
 * 목록, 등록, 수정, 삭제, 단건조회
 */
public interface BoardMapper {
	List<BoardVO> boardList(); // 전체목록.
	List<BoardVO> boardListPaging(int page); //페이지별로 5건씩.
	int getTotalCnt(); // 페이징 계산 용도.
	int insertBoard(BoardVO bvo);
	int updateBoard(BoardVO bvo);
	int deleteBoard(int bno);
	BoardVO selectBoard(int bno); //단건조회.
}
