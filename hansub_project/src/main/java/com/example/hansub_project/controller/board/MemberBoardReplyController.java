package com.example.hansub_project.controller.board;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.hansub_project.model.board.dto.MemberBoardReplyDTO;
import com.example.hansub_project.service.board.MemberBoardReplyService;


//@ResponseBody를 붙이지 않아도 뷰가 아닌 데이터를 리턴할 수 있다
@Controller
public class MemberBoardReplyController {

	@Inject
	MemberBoardReplyService memberboardreplyService;	//서비스를 호출하기 위해서 의존성을 주입함
	
	
	//로깅을 위한 변수
			private static final Logger logger=
			LoggerFactory.getLogger(MemberBoardReplyController.class);
	
	
	//댓글 리스트를 호출할때 맵핑되는 메소드
	@RequestMapping("/board/reply_list.do")
	public ModelAndView list(int member_bno, ModelAndView mav, MemberBoardReplyDTO dto,
			@RequestParam(value="curPage")int curPage,
	        @RequestParam(value="search_option") String search_option,
	        @RequestParam(value="keyword") String keyword
			) {
		List<MemberBoardReplyDTO> list = memberboardreplyService.list(member_bno);

		System.out.println("뷰에 전달할 데이터"+list);
		
		Map<String,Object> map = new HashMap<>();	//리스트의 값을 저장하기 위해 map객체를 생성하고 그 안에 리스트를 저장
		
		map.put("list", list);
		
		System.out.println("뷰에 전달할 데이터"+map);
		
		mav.addObject("map", map);	//뷰에 전달할 데이터 저장
		
	
		mav.setViewName("board/memberboardreply_list");	//뷰의 이름
		
		mav.addObject("curPage", curPage);
		mav.addObject("search_option", search_option);
		mav.addObject("keyword", keyword);

		
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
		
		if (session.getAttribute("user_id") != null) {
			
		String user_id = (String)session.getAttribute("user_id");
		dto.setUser_id(user_id);
		
		}
		
		
		//소셜로그인한 아이디도 받아와야 하기때문에 세션에 저장된 아이디값들을 추가함
		
		if (session.getAttribute("navername") != null) {
			
			String user_id = (String)session.getAttribute("navername");
			dto.setUser_id(user_id);
			
		}
		
		
		if (session.getAttribute("kakaonickname") != null) {
			
			String user_id = (String)session.getAttribute("kakaonickname");
			dto.setUser_id(user_id);
			
		}
		
		
		if (session.getAttribute("facebookname") != null) {
			
			String user_id = (String)session.getAttribute("facebookname");
			dto.setUser_id(user_id);
			
		}
		
		
		dto.setR_content(r_content);
		dto.setMember_bno(member_bno);
		
		
		//댓글이 테이블에 저장된다
		memberboardreplyService.create(dto);
		
	}
	
	
	//댓글 수정
	@RequestMapping("/board/reply_update.do")	//세부적인 url pattern
	public String reply_update (@RequestParam(value="rno") int rno, @RequestParam(value="r_content") String r_content, @RequestParam(value="user_id") String user_id,
			@RequestParam(value="curPage")int curPage, @RequestParam(value="search_option")String search_option, @RequestParam(value="keyword")String keyword,
			@RequestParam(value="member_bno")int member_bno, MemberBoardReplyDTO dto) throws Exception{
		
		dto.setRno(rno);
		dto.setR_content(r_content);
		dto.setUser_id(user_id);
		
		System.out.println("dto에 있는값들 출력함"+dto);

		memberboardreplyService.reply_update(dto);
		
		
		return "forward:/board/list.do";
	}
	
	
	//댓글 삭제
	@RequestMapping(value = "/board/reply_delete.do" , method = {RequestMethod.GET, RequestMethod.POST} )	//세부적인 url pattern
	public String reply_delete (@RequestParam(value="rno") int rno, MemberBoardReplyDTO dto, @RequestParam(value="member_bno") int member_bno,
			@RequestParam(value="curPage")int curPage, @RequestParam(value="search_option")String search_option, @RequestParam(value="keyword")String keyword) throws Exception{
		
		
		//파라미터로 받는 값은 자동적으로 String타입으로 변환되기 때문에 int타입으로 변환해주어야 한다.

		memberboardreplyService.delete(rno);
		
		
		return "forward:/board/view.do";
		
	}
	
	
	
}
