package com.example.hansub_project.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.hansub_project.model.admin.dto.AdminDTO;
import com.example.hansub_project.model.board.dto.AdminBoardDTO;


@Repository	//dao 빈을 선언
public class AdminBoardDAOImpl implements AdminBoardDAO {

	
	@Inject	//db에 접속하기 위한 의존관계를 주입
	SqlSession sqlSession;
	
	
	//게시글 목록
	@Override
	public List<AdminBoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {
		Map<String,Object> map = new HashMap<>();
		  map.put("search_option", search_option);
		  map.put("keyword", keyword);
	      map.put("start", start); //맵에 자료 저장
	      map.put("end", end);
		
		//매개변수는 시작 레코드의 번호, 끝 번호, 옵션과 키워드가 들어간다.
		return sqlSession.selectList("adminboard.listAll", map);
	}

	
	//게시글 쓰기
	@Override
	public void create(AdminBoardDTO dto) throws Exception {
		sqlSession.insert("adminboard.insert",dto);
		
	}
	
	
	//게시글 조회수 증가
	@Override
	public void increateViewcnt(int bno) throws Exception {
		sqlSession.update("adminboard.increaseViewcnt", bno);
		
	}

	//게시글 읽기
	@Override
	public AdminBoardDTO read(int bno) throws Exception {
		
		return sqlSession.selectOne("adminboard.read", bno);
	}

	//게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("adminboard.deleteArticle", bno); //mapper로 게시글 번호를 넘긴다.
		
	}

	//게시글 수정
	@Override
	public void update(AdminBoardDTO dto) throws Exception {
		sqlSession.update("adminboard.update", dto);
		
	}


}
