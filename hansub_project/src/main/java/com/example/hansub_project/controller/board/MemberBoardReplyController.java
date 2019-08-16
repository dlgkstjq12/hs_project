package com.example.hansub_project.controller.board;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.hansub_project.model.board.dto.MemberBoardReplyDTO;
import com.example.hansub_project.service.board.MemberBoardReplyService;


//@ResponseBody를 붙이지 않아도 뷰가 아닌 데이터를 리턴할 수 있다
@RestController
public class MemberBoardReplyController {

	@Inject
	MemberBoardReplyService memberboardreplyService;	//서비스를 호출하기 위해서 의존성을 주입함
	
	//댓글 리스트를 호출할때 맵핑되는 메소드
	@RequestMapping("/board/reply_list.do")
	public ModelAndView list(int member_bno, ModelAndView mav, MemberBoardReplyDTO dto) {
		List<MemberBoardReplyDTO> list = memberboardreplyService.list(member_bno);

		System.out.println("뷰에 전달할 데이터"+list);
		
		Map<String,Object> map = new HashMap<>();	//리스트의 값을 저장하기 위해 map객체를 생성하고 그 안에 리스트를 저장
		map.put("list", list);
		System.out.println("뷰에 전달할 데이터"+map);
		
		mav.addObject("map", map);	//뷰에 전달할 데이터 저장
		
	
		mav.setViewName("board/memberboardreply_list");	//뷰의 이름
		


		
		return mav;
	}
	
	
	//댓글 목록을 ArrayList로 리턴함
	@RequestMapping("/board/reply_list_json.do")
	public List<MemberBoardReplyDTO> list_json(int member_bno){
		
		return memberboardreplyService.list(member_bno);
		
	}
	
	//댓글 생성
	@RequestMapping("/board/reply_insert.do")	//세부적인 url pattern
	public void insert (MemberBoardReplyDTO dto, HttpSession session,
			 @RequestParam(value="r_content") String r_content,
			 @RequestParam(value="member_bno") int member_bno) {
		
		//댓글 작성자 아이디
		//현재 접속중인 사용자의 아이디
		String user_id = (String)session.getAttribute("user_id");
		
		dto.setUser_id(user_id);
		dto.setR_content(r_content);
		dto.setMember_bno(member_bno);
		
		//댓글이 테이블에 저장된다
		memberboardreplyService.create(dto);
		
	}
	
	
	//댓글 수정
	@RequestMapping("/board/reply_update.do")	//세부적인 url pattern
	public String reply_update (MemberBoardReplyDTO dto){
		
		memberboardreplyService.reply_update(dto);

		return "forward:/board/reply_list.do";
	}
	
	
	//댓글 삭제
	@RequestMapping("/board/reply_delete.do")	//세부적인 url pattern
	public String reply_delete (HttpServletRequest request) {
		
		
		System.out.println("번호출력값1"+request.getParameter("rno"));
		
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		System.out.println("번호출력값2"+rno);
		
		//파라미터로 받는 값은 자동적으로 String타입으로 변환되기 때문에 int타입으로 변환해주어야 한다.

		memberboardreplyService.delete(rno);
		
		return "/board/list.do";
	}
	
	
	
}
