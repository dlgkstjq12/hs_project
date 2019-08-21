package com.example.hansub_project.model.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.hansub_project.model.board.dto.MemberBoardReplyDTO;

@Repository		//dao 빈 설정
public class MemberBoardReplyDAOImpl implements MemberBoardReplyDAO {

	
	@Inject		//의존관계를 주입함
	SqlSession sqlSession;
	
	
	//댓글 목록
	@Override
	public List<MemberBoardReplyDTO> list(int member_bno) {
		System.out.println(member_bno);
		return sqlSession.selectList("reply.listReply", member_bno);
		
	}

	//댓글의 갯수
	@Override
	public int count(int member_bno) {
		return 0;
	}
	
	//댓글을 추가
	@Override
	public void create(MemberBoardReplyDTO dto) {
		sqlSession.insert("reply.insertReply", dto);
		
	}

	//댓글의 수정
	@Override
	public void reply_update(MemberBoardReplyDTO dto) {
		sqlSession.update("reply.updateReply", dto);
		
	}
	

	//댓글의 삭제
	@Override
	public void reply_delete(int rno) {
		sqlSession.delete("reply.deleteReply", rno);
	}

}
