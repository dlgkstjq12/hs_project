package com.example.hansub_project.service.board;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.hansub_project.model.board.dao.MemberBoardDAO;
import com.example.hansub_project.model.board.dto.MemberBoardDTO;

@Service	//서비스 빈으로 설정함
public class MemberBoardServiceImpl implements MemberBoardService {
	
	@Inject	//dao를 호출하기 때문에 의존성을 주입한다.
	MemberBoardDAO memberboarddao;

	@Override
	public void create(MemberBoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberBoardDTO read(int member_bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MemberBoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int member_bno) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MemberBoardDTO> listAll(String search_option, String keyword,int start, int end) throws Exception {

		return memberboarddao.listAll(search_option, keyword, start, end);
	}

	@Override
	public void increaseViewcnt(int member_bno, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}



}
