package com.example.hansub_project.controller.member;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.hansub_project.service.member.MemberService;
import com.example.hansub_project.controller.member.MemberController;
import com.example.hansub_project.model.member.dto.MemberDTO;

@Controller	//컨트롤러 빈 선언
public class MemberController {
	
	
	@Inject	//서비스를 호출하기 위해 의존성을 주입
	MemberService memberservice;
	
	//로깅을 위한 변수
		private static final Logger logger=
				LoggerFactory.getLogger(MemberController.class);

	
	//회원가입 페이지 맵핑 메소드
	@RequestMapping("/member/join.do")
	public String join() {
		return "member/join";
	}
	
	
	//회원가입 정보를 입력후 회원가입 버튼을 누르면 맵핑되는 메소드
	//여러개의 값들을 담아야 하므로 map에 회원의 정보들을 저장해 놓는다.
	@RequestMapping("/member/join.check.do")
	public ModelAndView joincheck(String user_id, String member_pass, String e_mail) {

		MemberDTO dto = new MemberDTO();
		Map<String, Object> map = new HashMap<>();
		ModelAndView mv = new ModelAndView();
		dto.setUser_id(user_id);
		dto.setMember_pass(member_pass);
		dto.setE_mail(e_mail);
		
		//값을 여러개담아야 하므로 해쉬맵을 사용해서 값을 저장함
		map.put("user_id", dto.getUser_id());
		map.put("member_pass", member_pass);
		map.put("e_mail",dto.getE_mail());
		
		memberservice.join(map,dto);
		
		mv.setViewName("member/joinresult");
		mv.addObject("user_id",user_id);
	
		return mv;
	}
	
	
		//로그인을 체크하는 메소드 (로그인이 성공하면 로그인 결과 페이지로 이동하고, 실패하면 다시 로그인 페이지로 이동한다.)
	  @RequestMapping("login.do") public ModelAndView login (String user_id, String
	  member_pass, HttpSession session) throws Exception{
	  
	  MemberDTO dto = new MemberDTO(); 
	  dto.setUser_id(user_id);
	  dto.setMember_pass(member_pass); 
	  boolean result = memberservice.loginCheck(dto, session); 
	  ModelAndView mav = new ModelAndView();
	  
	  if(result) { //로그인 성공 
	  mav.setViewName("member/login_result");//뷰의이름
	  mav.addObject("user_id", session.getAttribute(user_id));
	  
	  }else if(session.getAttribute(user_id)==null) { //로그인 실패
	  mav.setViewName("member/login"); 
	  //뷰에 전달할 값 
	 
	  mav.addObject("message","회원가입된 회원의 아이디 혹은 비밀번호가 일치하지 않습니다."); 
	  } 
	  	return mav; 
	}
	
	
	//로그아웃 메소드
	@RequestMapping("logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {
		
		//세션에 담긴값 초기화
		session.invalidate();	
		return "home";
	}
	
	
	//네이버 로그인 관련 페이지 이동 메소드
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		
		return "member/login";
		
	}
	
	
	//네이버 로그인 관련 페이지 이동 메소드
	@RequestMapping(value = "login_result", method = RequestMethod.GET)
	public String login_result(HttpSession session) {
		
		return "member/login_result";
	}
	
	
	//네이버 로그인 관련 페이지 이동 메소드
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(HttpSession session) {
		
		return "/home";
	}
	
	
	//아이디 찾기 페이지로 이동 
	@RequestMapping("find.user_id.do")
	public String finduser_id() {
		return "member/find_user_id";
	}
	
	
	//비밀번호 찾기 페이지로 이동
	@RequestMapping("find.member_pass.do")
	public String findmember_pass() {
		return "member/find_member_pass";
	}
	
	
	//아이디 찾기 처리
	@RequestMapping("find_id.do")
	public ModelAndView find_id(String e_mail) {
		ModelAndView mav = new ModelAndView();
		MemberDTO dto = new MemberDTO();
		
		//dto에 이메일 값을 저장해서 그 이메일값을 사용해서 아이디를 검색함
		dto.setE_mail(e_mail);
		String user_id = memberservice.find_idCheck(dto);
		
		if(user_id != null) {
			mav.setViewName("member/find_id_result");	
			mav.addObject("user_id", user_id);
		
		}else {
			//아이디 찾기 실패
			mav.setViewName("member/find_user_id");
			//뷰에 전달할 값
			mav.addObject("message", "회원가입된 회원의 이메일이 아닙니다");
		}
		
		return mav;
	}
	
		//비밀번호 찾기 처리
		@RequestMapping("find_pass.do")
		public ModelAndView find_pass(String user_id, String e_mail) {
			ModelAndView mav = new ModelAndView();
			MemberDTO dto = new MemberDTO();
			
			dto.setUser_id(user_id);
			dto.setE_mail(e_mail);
			
			String member_pass = memberservice.find_passCheck(dto);
			
			if(member_pass != null) {
				mav.setViewName("member/find_pass_result");	
				mav.addObject("member_pass", member_pass);
			
			}else {
				//비밀번호 찾기 실패
				mav.setViewName("member/find_member_pass");
				//뷰에 전달할 값
				mav.addObject("message", "회원가입된 회원의 아이디 혹은 이메일이 아닙니다.");
		}	
			return mav;
	}
} 