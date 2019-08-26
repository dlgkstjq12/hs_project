package com.example.hansub_project.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.hansub_project.model.board.dto.MemberBoardDTO;
import com.example.hansub_project.model.member.dao.MemberDAO;


@Repository		//dao 선언
public class MemberBoardDAOImpl implements MemberBoardDAO {

	@Inject	//db에 접속하기 위해 의존관계를 주입
	SqlSession sqlSession; 
	
	
	//게시글 쓰기
	@Override
	public void create(MemberBoardDTO dto) throws Exception {
		sqlSession.insert("memberboard.insert",dto);
		
	}

	//게시글 수정 
	@Override
	public void update(MemberBoardDTO dto) throws Exception {
		sqlSession.update("memberboard.update", dto);
		
	}

	//게시물 삭제 관련
	@Override
	public void delete(int member_bno) throws Exception {
		sqlSession.delete("memberboard.deleteArticle", member_bno); //mapper로 게시글 번호를 넘긴다.
		
	}
	
	//게시물 목록을 리턴
	@Override
	public List<MemberBoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {
		
		Map<String,Object> map = new HashMap<>();
		  map.put("search_option", search_option);
		  map.put("keyword", keyword);
	      map.put("start", start); //맵에 자료 저장
	      map.put("end", end);
		
		//매개변수는 시작 레코드의 번호, 끝 번호, 옵션과 키워드가 들어간다.
		return sqlSession.selectList("memberboard.listAll", map);
	}
	
	//조회수 증가처리를 하는 메소드
	@Override
	public void increateViewcnt(int member_bno) throws Exception {
		
		sqlSession.update("memberboard.increaseViewcnt", member_bno);
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {

		Map<String,String> map=new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", "%"+keyword+"%");
		return sqlSession.selectOne("memberboard.countArticle",map);
	}

	//게시글 상세정보 
	@Override
	public MemberBoardDTO read(int member_bno) throws Exception {
		
		return sqlSession.selectOne("memberboard.read", member_bno);
	}

	//추천수 증가처리 메소드
	@Override
	public void recommend(int member_bno) throws Exception {
		
		sqlSession.update("memberboard.recommend", member_bno);
	}


	
}
	
	