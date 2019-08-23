package com.example.hansub_project.model.board.dao;

import java.util.List;

import com.example.hansub_project.model.admin.dto.AdminDTO;
import com.example.hansub_project.model.board.dto.AdminBoardDTO;

public interface AdminBoardDAO {

	public List<AdminBoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception;	//게시물 목록

	public void create(AdminBoardDTO dto) throws Exception;	//게시글 작성

	public void increateViewcnt(int bno) throws Exception;	//게시글 조회

	public AdminBoardDTO read(int bno) throws Exception;	//게시글 읽기

	public void delete(int bno) throws Exception;	//게시글 삭제

	public void update(AdminBoardDTO dto) throws Exception;		//게시글 수정


}
