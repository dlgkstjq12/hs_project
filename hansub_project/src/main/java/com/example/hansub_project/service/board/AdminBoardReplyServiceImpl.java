package com.example.hansub_project.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.hansub_project.model.board.dao.AdminBoardReplyDAO;
import com.example.hansub_project.model.board.dto.AdminBoardReplyDTO;


@Service //서비스 빈을 생성
public class AdminBoardReplyServiceImpl implements AdminBoardReplyService {
	
	@Inject
	AdminBoardReplyDAO adminboardreplydao;	//dao를 호출하기 위해 의존성을 주입함

	//댓글의 목록
	@Override
	public List<AdminBoardReplyDTO> list(int bno) {
		
		return adminboardreplydao.list(bno);
	}

	//댓글을 수정
	@Override
	public void reply_update(AdminBoardReplyDTO dto) {
		adminboardreplydao.reply_update(dto);
		
	}

	//댓글의 생성
	@Override
	public void create(AdminBoardReplyDTO dto) {
		adminboardreplydao.create(dto);
		
	}

	
	//댓글을 삭제
	@Override
	public void delete(int rno) {
		adminboardreplydao.delete(rno);
		
	}

	
	//댓글 갯수
	@Override
	public int count(int bno) {
		return 0;
	}

}
