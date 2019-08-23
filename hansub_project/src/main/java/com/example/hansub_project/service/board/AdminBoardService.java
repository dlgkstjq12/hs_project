package com.example.hansub_project.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.hansub_project.model.admin.dto.AdminDTO;
import com.example.hansub_project.model.board.dto.AdminBoardDTO;

public interface AdminBoardService {

	public List<AdminBoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception;	//게시판목록 출력 메소드

	public void create(AdminBoardDTO dto) throws Exception;		//게시글 생성

	public void increaseViewcnt(int bno, HttpSession session) throws Exception;		//게시물 조회수 증가

	public AdminBoardDTO read(int bno) throws Exception;		//게시글 읽기 매소드

	public void delete(int bno) throws Exception;		//게시글 삭제

	public void update(AdminBoardDTO dto) throws Exception;		//게시글 수정

}
