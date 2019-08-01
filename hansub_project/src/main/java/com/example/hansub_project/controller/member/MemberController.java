package com.example.hansub_project.controller.member;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hansub_project.service.member.MailService;
import com.example.hansub_project.service.member.MemberService;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.example.hansub_project.controller.member.MemberController;
import com.example.hansub_project.model.member.dto.MemberDTO;


@Controller	//컨트롤러 빈 선언
public class MemberController {
	
	
	@Inject	
	MemberService memberservice; //서비스를 호출하기 위해 의존성을 주입
	private MailService mailService; //이메일 관련 서비스

	
	//로깅을 위한 변수
	private static final Logger logger=
	LoggerFactory.getLogger(MemberController.class);
	
	
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	//회원가입 이메일 인증받는 메소드
	@RequestMapping(value = "/member/auth.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean sendMailAuth(HttpSession session, String e_mail) {
        int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String joinCode = String.valueOf(ran);
        session.setAttribute("joinCode", joinCode);
 
        System.out.println("1번"+e_mail);
        String subject = "회원가입 인증 코드 발급 안내 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");
        return mailService.send(subject, sb.toString(), "아이디@gmail.com", e_mail, "0");

	}
	

	
	//이메일 인증 페이지 맵핑 메소드
	@RequestMapping("/member/email.do")
	public String email() {
		return "member/email";
	}
	
	
	//회원가입 페이지 맵핑 메소드
		@RequestMapping("/member/join.do")
		public String join() {
			return "member/join";
		}
	
	
	//회원가입 정보를 입력후 회원가입 버튼을 누르면 맵핑되는 메소드
	//여러개의 값들을 담아야 하므로 map에 회원의 정보들을 저장해 놓는다.
	@RequestMapping("/member/join_check.do")
	public ModelAndView joincheck(String user_id, String member_pass, String e_mail, Model model, HttpServletRequest request) {

		MemberDTO dto = new MemberDTO();
		Map<String, Object> map = new HashMap<>();
		ModelAndView mv = new ModelAndView();

		
		//map에 저장해서 값을 넘기기 위해 dto에 아이디, 비밀번호, 메일주소를 저장함
		dto.setUser_id(user_id);
		dto.setMember_pass(member_pass);
		dto.setE_mail(e_mail);
		
		System.out.println("1번 dto"+dto);
		System.out.println("2번 request"+request);
	
		//값을 여러개담아야 하므로 해쉬맵을 사용해서 값을 저장함
		map.put("user_id", dto.getUser_id());
		map.put("member_pass", member_pass);
		map.put("e_mail",dto.getE_mail());
		
		memberservice.join(map,dto);
		
		
		//modelview에 보낼 id값과 페이지를 지정함
		mv.setViewName("member/joinresult");
		mv.addObject("user_id",user_id);
	

		return mv;
	}
	
	
	
		//로그인을 체크하는 메소드 (로그인이 성공하면 로그인 결과 페이지로 이동하고, 실패하면 다시 로그인 페이지로 이동한다.)
	  @RequestMapping("normale_login.do") public ModelAndView login (String user_id, String
	  member_pass, HttpSession session) throws Exception{
	  
		  //로그인 체크를 위해 id와 비밀번호를 dto에 저장
	  MemberDTO dto = new MemberDTO(); 
	  dto.setUser_id(user_id);
	  dto.setMember_pass(member_pass); 
	  
	  
	  boolean result = memberservice.loginCheck(dto, session); 
	  ModelAndView mav = new ModelAndView();
	  
	  if(result) { //로그인 성공 (result값이 참일때 실행되는 구문) 
	  mav.setViewName("home");//뷰의이름
	  mav.addObject("user_id", session.getAttribute(user_id));
	  
	  }else if(session.getAttribute(user_id)==null) { //로그인 실패
	  mav.setViewName("member/login"); 
	  //뷰에 전달할 값 
	 
	  mav.addObject("message","회원가입된 회원의 아이디 혹은 비밀번호가 일치하지 않습니다."); 
	  } 
	  	return mav; 
	}
	
	  
	//일반 로그아웃 메소드
		@RequestMapping("logout.do")
		public String logout(HttpSession session, HttpServletRequest request) {
			
			//세션에 담긴값 초기화
			session.invalidate();
			
			return "home";
		} 
	  
	//네이버 관련 로그아웃 메소드
	@RequestMapping("naver_logout.do")
	public String naver_logout(HttpSession session, HttpServletRequest request) {
		
		//세션에 담긴값 초기화
		session.invalidate();
		
		return "home";
	}
	
	
		//카카오톡 관련 로그아웃 메소드
		@RequestMapping("kakao_logout.do")
		public String kakao_logout(HttpSession session, HttpServletRequest request) {
			
			//세션에 담긴값 초기화
			session.invalidate();
			
			return "home";
		}
		
		
		//페이스북 관련 로그아웃 메소드
		@RequestMapping("facebook_logout.do")
		public String facebook_logout(HttpSession session, HttpServletRequest request) {
			
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
		
		//문자를 보낼때 맵핑되는 메소드
		@RequestMapping(value = "/sendSms.do")
		  public String sendSms(HttpServletRequest request) throws Exception {

		    String api_key = "NCS0VWZPZQPVXEXX";
		    String api_secret = "DKTPX4RYWG5GEDPL6DMRRNKOJMATK24X";
		    com.example.hansub_project.Coolsms coolsms = new com.example.hansub_project.Coolsms(api_key, api_secret);

		    HashMap<String, String> set = new HashMap<String, String>();
		    set.put("to", "01072851455"); // 수신번호

		    set.put("from", (String)request.getParameter("from")); // 발신번호
		    set.put("text", (String)request.getParameter("text")); // 문자내용
		    set.put("type", "sms"); // 문자 타입

		    System.out.println(set);

		    JSONObject result = coolsms.send(set); // 보내기&전송결과받기

		    if ((boolean)result.get("status") == true) {
		      // 메시지 보내기 성공 및 전송결과 출력
		      System.out.println("성공");
		      System.out.println(result.get("group_id")); // 그룹아이디
		      System.out.println(result.get("result_code")); // 결과코드
		      System.out.println(result.get("result_message")); // 결과 메시지
		      System.out.println(result.get("success_count")); // 메시지아이디
		      System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
		    } else {
		      // 메시지 보내기 실패
		      System.out.println("실패");
		      System.out.println(result.get("code")); // REST API 에러코드
		      System.out.println(result.get("message")); // 에러메시지
		    }

		    return "member/number";
		  }
		
		
		
		
		
		
		
}