package com.example.hansub_project.model.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.hansub_project.model.board.dto.AdminBoardReplyDTO;


@Repository	//dao 빈을 설정함
public class AdminBoardReplyDAOImpl implements AdminBoardReplyDAO {

	
	@Inject	//의존 관계를 주입함
	SqlSession sqlSession;
	
	
	//댓글 목록 출력
	@Override
	public List<AdminBoardReplyDTO> list(int bno) {
		return sqlSession.selectList("admin_board_reply.listReply", bno);
	}
	
	
	//댓글 수정
	@Override
	public void reply_update(AdminBoardReplyDTO dto) {
		sqlSession.update("admin_board_reply.updateReply", dto);
	}

	//댓글 생성
	@Override
	public void create(AdminBoardReplyDTO dto) {
		sqlSession.insert("admin_board_reply.insertReply", dto);
	}

	
	//댓글 삭제
	@Override
	public void delete(int rno) {
		sqlSession.delete("admin_board_reply.deleteReply",rno);
	}
	

	//댓글 갯수
	@Override
	public int count(int member_bno) {
		return 0;
	}

}
