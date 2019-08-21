package com.example.hansub_project.service.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.hansub_project.model.board.dto.MemberBoardDTO;


public interface MemberBoardService {

	public void create(MemberBoardDTO dto) throws Exception;		//글 쓰기
	public MemberBoardDTO read(int member_bno) throws Exception;	//글 읽기
	public void update (MemberBoardDTO dto) throws Exception;		//글 수정
	public void delete (int member_bno) throws Exception;			//글 삭제
	
	
	//목록 (페이지 나누기, 검색 기능을 포함)
	//매개변수는 시작 레코드 번호, 끝번호, 옵션과 키워드가 들어간다.
	public List<MemberBoardDTO> listAll(String search_option, String keyword, int start, int end)throws Exception;
	
	//조회수를 증가 처리
	public void increaseViewcnt(int member_bno, HttpSession session) throws Exception;
	
	
	//레코드 갯수를 계산
	public int countArticle(String search_option, String keyword) throws Exception;
	
	public void recommend(int member_bno) throws Exception;	//게시글 추천관련
	
}
