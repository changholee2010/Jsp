package co.yedam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.yedam.vo.ReplyVO;

public interface ReplyMapper {
	// 목록,단건조회,등록,삭제
	List<ReplyVO> selectList(int boardNo);
	List<ReplyVO> selectListPaging(@Param("boardNo") int b, @Param("page") int p);
	ReplyVO selectReply(int replyNo);
	int insertReply(ReplyVO rvo);
	int deleteReply(int replyNo);
	
	//댓글건수.
	int selectReplyCnt(int bno);
}
