package com.example.hansub_project.model.board.dao;

import java.util.List;


import com.example.hansub_project.model.board.dto.MemberBoardDTO;

//DAO 클래스
public interface MemberBoardDAO {

	public void create(MemberBoardDTO dto) throws Exception;	//글 쓰기
	public void update(MemberBoardDTO dto) throws Exception;	//글 수정
	public void delete(int member_bno) throws Exception;				//글 삭제
	
	public List<MemberBoardDTO> listAll(String search_option, String keyword,int start, int end) throws Exception; 	//게시글 리스트를 출력하는 메소드
	
	public void increateViewcnt(int member_bno) throws Exception;	//조회수 증가 처리
	
	public int countArticle(String search_option, String keyword) throws Exception;	//레코드 갯수 계산
	
	public MemberBoardDTO read (int member_bno) throws Exception;	//레코드 조회
	
	
	public void recommend(int member_bno) throws Exception;		//추천하기 메소드
	
}
