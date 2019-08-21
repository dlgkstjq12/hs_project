package com.example.hansub_project.service.board;

import java.util.List;

import com.example.hansub_project.model.board.dto.MemberBoardReplyDTO;

public interface MemberBoardReplyService {
	
	public List<MemberBoardReplyDTO> list(int member_bno);	//댓글의 리스트

	public void create(MemberBoardReplyDTO dto);	//댓글 생성

	public int count(int member_bno);	//댓글 갯수

	public void reply_update(MemberBoardReplyDTO dto);	//댓글 수정

	public void delete(int rno);	//댓글을 삭제
	

}
