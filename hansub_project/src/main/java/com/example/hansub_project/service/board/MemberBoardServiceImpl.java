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
		memberboarddao.create(dto);
		//dto를 매개값으로 dao를 호출한다.
	}

	//게시물 읽기
	@Override
	public MemberBoardDTO read(int member_bno) throws Exception {
		return memberboarddao.read(member_bno);
	}

	//게시글 수정
	@Override
	public void update(MemberBoardDTO dto) throws Exception {
		memberboarddao.update(dto);
		
	}
	
	//게시물 삭제 관련 메소드
	@Override
	public void delete(int member_bno) throws Exception {
		memberboarddao.delete(member_bno);
		
	}

	@Override
	public List<MemberBoardDTO> listAll(String search_option, String keyword,int start, int end) throws Exception {

		return memberboarddao.listAll(search_option, keyword, start, end);
	}

	
	//조회수를 증가하게하는 쿼리
	//조회수 처리를 할때 일정 시간이 지난후 다시 클릭할때만 조회수가 증가하도록 설정
	@Override
	public void increaseViewcnt(int member_bno, HttpSession session) throws Exception {
		long update_time = 0; //null을 방지하기 위해 초기값을 null로 설정함
		if(session.getAttribute("update_time_"+member_bno)!=null) {
			
			//최근에 조회수를 올린 시간이 null이 아니면
			update_time = (long)session.getAttribute("update_time_"+member_bno);
		}
		
		long current_time = System.currentTimeMillis();
		
		//일정 시간이 경과한 후에 조회수를 증가시킨다.
		if(current_time - update_time > 5 * 1000) {
			
		//조회수가 1증가했을때로부터 5000초 후에 다시 클릭을 해야 조회수가 다시 1 증가한다는 말이다.
		//조회수 증가 처리
			memberboarddao.increateViewcnt(member_bno);
			
			//조회수를 올린 시간을 저장함
			session.setAttribute("update_time_"+member_bno, current_time);
			
		}
		
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		return memberboarddao.countArticle(search_option,keyword); 
	}

	//게시글 추천관련 메소드 구현
	@Override
	public void recommend(int member_bno) throws Exception {
		
		memberboarddao.recommend(member_bno);
		
		}

	}


