package com.example.hansub_project.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.hansub_project.model.admin.dto.AdminDTO;
import com.example.hansub_project.model.board.dao.AdminBoardDAO;
import com.example.hansub_project.model.board.dto.AdminBoardDTO;


@Service //서비스 관련 빈으로 설정
public class AdminBoardServiceImpl implements AdminBoardService {

	
	
	@Inject	//dao를 호출하기 때문에 의존성을 주입한다.
	AdminBoardDAO adminboarddao;
	
	
	//게시글 목록 출력
	@Override
	public List<AdminBoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {

		return adminboarddao.listAll(search_option, keyword, start, end);
	}

	//글쓰기 
	@Override
	public void create(AdminBoardDTO dto) throws Exception {
			adminboarddao.create(dto);
		//dto를 매개값으로 dao를 호출한다.
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time = 0; //null을 방지하기 위해 초기값을 null로 설정함
		if(session.getAttribute("update_time_"+bno)!=null) {
			
			//최근에 조회수를 올린 시간이 null이 아니면
			update_time = (long)session.getAttribute("update_time_"+bno);
		}
		
		long current_time = System.currentTimeMillis();
		
		//일정 시간이 경과한 후에 조회수를 증가시킨다.
		if(current_time - update_time > 5 * 1000) {
			
		//조회수가 1증가했을때로부터 5000초 후에 다시 클릭을 해야 조회수가 다시 1 증가한다는 말이다.
		//조회수 증가 처리
			adminboarddao.increateViewcnt(bno);
			
			//조회수를 올린 시간을 저장함
			session.setAttribute("update_time_"+bno, current_time);
			
		}
	}
	
	//게시물 읽기
	@Override
	public AdminBoardDTO read(int bno) throws Exception {
		return adminboarddao.read(bno);
	}

	
	//게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		adminboarddao.delete(bno);
	}

	
	//게시글 수정
	@Override
	public void update(AdminBoardDTO dto) throws Exception {
		adminboarddao.update(dto);
		
	}

}
