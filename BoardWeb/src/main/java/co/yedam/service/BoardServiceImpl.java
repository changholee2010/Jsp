package co.yedam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import co.yedam.common.DataSource;
import co.yedam.common.SearchVO;
import co.yedam.mapper.BoardMapper;
import co.yedam.vo.BoardVO;
import co.yedam.vo.MemberVO;

/*
 * 업무프로세스를 따라 실행하기 위한 서비스.
 */
public class BoardServiceImpl implements BoardService {
	SqlSession sqlSession //
			= DataSource.getInstance().openSession(true);
	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

	@Override
	public List<BoardVO> boardList(SearchVO search) {
		// mapper등록된 기능 활용.
		return mapper.boardListPaging(search);
	}

	@Override
	public int boardTotal(SearchVO search) {
		return mapper.getTotalCnt(search);
	}

	@Override
	public BoardVO getBoard(int bno) {
		return mapper.selectBoard(bno);
	}

	@Override
	public boolean addBoard(BoardVO bvo) {
		return mapper.insertBoard(bvo) == 1;
	}

	@Override
	public boolean editBoard(BoardVO bvo) {
		return mapper.updateBoard(bvo) == 1;
	}

	@Override
	public boolean removeBoard(int bno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MemberVO checkMember(String id, String pw) {
		return mapper.selectMember(id, pw);
	}

	@Override
	public Map<String, String> addMember(MemberVO mvo) {
		Map<String, String> result = new HashMap<String, String>();

		if (mapper.selectMemberId(mvo.getUserId()) == 1) {
			result.put("retCode", "NG");
			result.put("message", "이미 존재하는 아이디입니다");
			return result;
		}
		mapper.insertMember(mvo);
		result.put("retCode", "OK");
		result.put("message", "정상등록 완료.");
		return result;
	}

	@Override
	public List<MemberVO> memberList() {
		return mapper.memberList();
	}

	@Override
	public boolean addMemberAjax(MemberVO mvo) {
		return mapper.insertMemberAjax(mvo) == 1;
	}

	@Override
	public boolean checkMemberId(String id) {
		return mapper.selectMemberAjax(id) == 1;
	}

	@Override
	public boolean delMemberAjax(String id) {
		return mapper.deleteMemberAjax(id) == 1;
	}
}
