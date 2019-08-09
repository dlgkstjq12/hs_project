package com.example.hansub_project.controller.board;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.hansub_project.Pager;
import com.example.hansub_project.controller.member.MemberController;
import com.example.hansub_project.model.board.dto.MemberBoardDTO;
import com.example.hansub_project.service.board.MemberBoardService;
import com.example.hansub_project.service.member.MemberService;


@Controller	//게시판 관련 컨트롤러를 선언함
public class MemberBoardController {
	
	@Inject		//서비스를 호출하기위해서 의존성을 주입함
	MemberBoardService memberboardservice;
	
	//로깅을 위한 변수
		private static final Logger logger=
		LoggerFactory.getLogger(MemberBoardController.class);
	
	@RequestMapping("list.do")	//세부적인 url mapping
	public ModelAndView list(//RequestParam으로 옵션, 키워드, 페이지의 기본값을 각각 설정해준다.
			
			@RequestParam(defaultValue="1") int curPage,
			@RequestParam(defaultValue="user_id") String search_option,
			@RequestParam(defaultValue="") String keyword

			)
			 throws Exception{
		
		//레코드 갯수를 계산
		int count = 1000;
		
		//페이지 관련 설정, 시작번호와 끝번호를 구해서 각각 변수에 저장한다.
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end =  pager.getPageEnd();
		
		System.out.println(start);
		System.out.println(end);
		
		List<MemberBoardDTO> list = memberboardservice.listAll(search_option, keyword, start, end);
		
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();	//넘길 데이터가 많기 때문에 해쉬맵에 저장한 후에 modelandview로 값을 넣고 페이지를 지정
		
		map.put("list", list); 						//map에 list(게시글 목록)을 list라는 이름의 변수로 자료를 저장함.
		map.put("pager", pager);
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		mav.addObject("map", map);					//modelandview에 map를 저장
		
		System.out.println("map : "+map);
		mav.setViewName("board/memberboard");				//자료를 넘길 뷰의 이름
		
		return mav;	//게시판 페이지로 이동
	
	}

}
