package com.example.hansub_project.model.board.dao;

import java.util.List;

import com.example.hansub_project.model.board.dto.MemberBoardReplyDTO;

public interface MemberBoardReplyDAO {
	
	public List<MemberBoardReplyDTO> list(int member_bno);	//댓글의 목록
	
	public int count(int member_bno);			//댓글의 갯수
	
	public void create(MemberBoardReplyDTO dto);		//댓글의 작성

	public void reply_update(MemberBoardReplyDTO dto);		//댓글의 수정

	public void reply_delete(int rno);		//댓글의 삭제


}
