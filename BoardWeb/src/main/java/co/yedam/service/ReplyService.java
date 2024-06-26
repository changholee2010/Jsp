package co.yedam.service;

import java.util.List;
import java.util.Map;

import co.yedam.common.CenterVO;
import co.yedam.vo.ReplyVO;

public interface ReplyService {
	//목록,단건,입력,삭제
	List<ReplyVO> replyList(int boardNo, int page);
	ReplyVO getReply(int replyNo);
	boolean registerReply(ReplyVO rvo);
	boolean removeReply(int replyNo);
	
	// 댓글건수.
	int getTotalCount(int bno);
	
	// 센터정보 생성.
	int createCenterInfo(CenterVO[] array);
	List<Map<String, Object>> centerList();
}
