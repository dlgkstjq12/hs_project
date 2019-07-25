package com.example.hansub_project.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
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

@Controller
public class MemberController {
	
	
	@Inject
	MemberService memberservice;
	
	//로깅을 위한 변수
		private static final Logger logger=
				LoggerFactory.getLogger(MemberController.class);

	@RequestMapping("/member/join.do")
	public String join() {
		return "member/join";
	}
	
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
	public String logout(HttpSession session) {
		
		//세션에 담긴값 초기화
		session.invalidate();
		
		return "home";
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		
		return "member/login";
		
	}
	
	
	
	
	@RequestMapping(value = "login_result", method = RequestMethod.GET)
	public String login_result(HttpSession session) {
		
		return "member/login_result";
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