package com.example.hansub_project.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.hansub_project.model.board.dao.MemberBoardReplyDAO;
import com.example.hansub_project.model.board.dto.MemberBoardReplyDTO;


@Service	//서비스 빈 생성
public class MemberBoardReplyServiceImpl implements MemberBoardReplyService {

	
	@Inject
	MemberBoardReplyDAO memberboardreplydao;	//dao를 호출하기 위해 의존성을 주입
	
	
	//댓글의 목록
	@Override
	public List<MemberBoardReplyDTO> list(int member_bno) {
		return memberboardreplydao.list(member_bno);
	}

	//댓글 생성
	@Override
	public void create(MemberBoardReplyDTO dto) {
		memberboardreplydao.create(dto);

	}
	
	//댓글 갯수
	@Override
	public int count(int member_bno) {
		return 0;
	}

	//댓글 수정
	@Override
	public void reply_update(MemberBoardReplyDTO dto) {
		memberboardreplydao.reply_update(dto);		
	}

	//댓글 삭제
	@Override
	public void delete(int rno) {
		memberboardreplydao.reply_delete(rno);
		
	}

}
