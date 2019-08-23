package com.example.hansub_project.service.board;

import java.util.List;

import com.example.hansub_project.model.board.dto.AdminBoardReplyDTO;

public interface AdminBoardReplyService {

	public List<AdminBoardReplyDTO> list(int bno); 	//댓글의 리스트

	public void reply_update(AdminBoardReplyDTO dto);	//댓글 수정

	public void create(AdminBoardReplyDTO dto);		//댓글 생성

	public void delete(int rno);	//댓글 삭제
	
	public int count(int bno);	//댓글의 갯수

}
