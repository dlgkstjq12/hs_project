package com.example.hansub_project.model.board.dao;

import java.util.List;

import com.example.hansub_project.model.board.dto.AdminBoardReplyDTO;

public interface AdminBoardReplyDAO {

	public List<AdminBoardReplyDTO> list(int bno);	//댓글의 목록

	public void reply_update(AdminBoardReplyDTO dto);		//댓글의 수정

	public void create(AdminBoardReplyDTO dto);		//댓글의 생성

	public void delete(int rno);		//댓글의 삭제
	
	public int count(int member_bno);			//댓글의 갯수

}
