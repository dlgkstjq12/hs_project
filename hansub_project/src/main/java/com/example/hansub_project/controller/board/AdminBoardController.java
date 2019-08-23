package com.example.hansub_project.controller.board;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.hansub_project.Pager;
import com.example.hansub_project.model.board.dto.AdminBoardDTO;
import com.example.hansub_project.model.board.dto.MemberBoardDTO;
import com.example.hansub_project.service.board.AdminBoardService;


@Controller	//게시판 관련 컨트롤러를 선언함
public class AdminBoardController {
	
	
	@Inject		//서비스를 호출하기위해서 의존성을 주입함
	AdminBoardService adminboardservice;
	
	
	//로깅을 위한 변수
	private static final Logger logger=
	LoggerFactory.getLogger(AdminBoardController.class);
	
	
	@RequestMapping("/board/admin_board_list.do")	//세부적인 url mapping
	public ModelAndView list(//RequestParam으로 옵션, 키워드, 페이지의 기본값을 각각 설정해준다.
			
			@RequestParam(defaultValue="1") int curPage,
			@RequestParam(defaultValue="admin_id") String search_option,
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
		
		List<AdminBoardDTO> list = adminboardservice.listAll(search_option, keyword, start, end);
		
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();	//넘길 데이터가 많기 때문에 해쉬맵에 저장한 후에 modelandview로 값을 넣고 페이지를 지정
		
		map.put("list", list); 						//map에 list(게시글 목록)을 list라는 이름의 변수로 자료를 저장함.
		map.put("pager", pager);
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
	
		mav.addObject("map", map);					//modelandview에 map를 저장
		
		
		System.out.println("map : "+map);
		mav.setViewName("board/adminboard");				//자료를 넘길 뷰의 이름
		
		return mav;	//게시판 페이지로 이동
	
	}
	
	
	
	//글쓰기 버튼을 눌렀을때 뷰에서 맵핑되는 메소드
		@RequestMapping("/board/admin_board_write.do")
			public String write(HttpSession session, HttpServletResponse write) throws Exception{
			//글쓰기 폼 페이지로 이동함
			
			if (session.getAttribute("admin_id") != null) {
				
				String admin_id = (String)session.getAttribute("admin_id");
				
				}else
			
			if (session.getAttribute("admin_id") == null ) {
				
				write.setContentType("text/html; charset=UTF-8");
	            PrintWriter out_write = write.getWriter();
	            out_write.println("<script>alert('로그인이 되어있지 않습니다. 로그인을 먼저 해주세요.');</script>");
	            out_write.flush();
				
	            return "home";
	            
			} 
			
			return "board/adminboardwrite";	//글쓰기 폼으로 이동함
		}
		
		//write.jsp에서 입력한 내용들이 AdminBoardDTO에 저장됨
		@RequestMapping("/board/admin_board_insert.do")
		public String insert (@ModelAttribute AdminBoardDTO dto, HttpSession session, HttpServletResponse insert) throws Exception{
			
			//로그인한 사용자의 아이디를 체크
			
			if (session.getAttribute("admin_id") != null) {
			
			String admin_id = (String)session.getAttribute("admin_id");
			
			dto.setAdmin_id(admin_id);
			
			}
			
			insert.setContentType("text/html; charset=UTF-8");
	        PrintWriter out_write = insert.getWriter();
	        out_write.println("<script>alert('글이 작성되었습니다.');</script>");
	        out_write.flush();
	        
	        
	        	//레코드를 저장함
	        adminboardservice.create(dto);
			
			
			//게시물을 저장한 후에 게시물 목록페이지로 다시 이동함
			return "forward:/board/admin_board_list.do";
			}
		
		//게시글 상세보기
		@RequestMapping(value = "/board/admin_board_view.do", method= {RequestMethod.GET, RequestMethod.POST})
		public ModelAndView view(@RequestParam int bno,
				@RequestParam int curPage,
		        @RequestParam String search_option,
		        @RequestParam String keyword,
		        HttpSession session) throws Exception{
			
			//조회수 증가 쿼리
			adminboardservice.increaseViewcnt(bno, session);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("board/adminboardview");
			
			//view로 자료를 넘기기위해서 mav에 값들을 저장해서 view.jsp로 리턴시킨다.
			mav.addObject("dto", adminboardservice.read(bno)); //상세보기를 한번 클릭하면 조회수를 1증가시킨다.
			mav.addObject("curPage", curPage);
			mav.addObject("search_option", search_option);
			mav.addObject("keyword", keyword);
			
			return mav; 	//view로 넘어가서 출력이 된다.
		}
		
		//게시물 삭제 관련 메소드
		@RequestMapping("/board/admin_board_delete.do")
		public String delete (int bno) throws Exception{
			adminboardservice.delete(bno);	//삭제 처리
			return "forward:/board/admin_board_list.do";	//게시물 삭제후 게시물 리스트페이지로 이동함
		
		}
		
		
		//게시물 수정 관련 메소드
		@RequestMapping("/board/admin_board_update.do")
		public String update (AdminBoardDTO dto) throws Exception{
			
			adminboardservice.update(dto);	//게시글 수정 처리
			return "forward:/board/admin_board_list.do";	//게시글 수정후 게시물 리스트 페이지로 이동함
		}
		
		
}
