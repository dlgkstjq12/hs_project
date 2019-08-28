package com.example.hansub_project.controller.member;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hansub_project.service.member.MemberService;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.api.SenderID;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.example.hansub_project.controller.member.MemberController;
import com.example.hansub_project.model.member.dto.MemberDTO;


@Controller	//컨트롤러 빈 선언
public class MemberController {
	
	
	@Inject	//서비스를 호출하기 위해서 의존성을 주입
	JavaMailSender mailSender; 	//메일 서비스를 사용하기 위해 의존성을 주입함.
	
	
	@Inject
	MemberService memberservice; //서비스를 호출하기 위해 의존성을 주입
	
	
	//로깅을 위한 변수
	private static final Logger logger=
	LoggerFactory.getLogger(MemberController.class);
	private static final String String = null;
	
	
	// mailSending 코드 (회원가입시 이메일 인증 메소드.)
		@RequestMapping( value = "/member/auth.do" , method=RequestMethod.POST )
		public ModelAndView mailSending(HttpServletRequest request, String e_mail, HttpServletResponse response_email) throws IOException {

			Random r = new Random();
			int dice = r.nextInt(4589362) + 49311;
			
			String setfrom = "dlgkstjq623@gamil.com";
			String tomail = request.getParameter("e_mail"); // 받는 사람 이메일
			String title = "회원가입 인증 이메일 입니다."; // 제목
			String content =
			
			System.getProperty("line.separator")+
			
			System.getProperty("line.separator")+
					
			"안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다"
			
			+System.getProperty("line.separator")+
			
			System.getProperty("line.separator")+
	
			" 인증번호는 " +dice+ " 입니다. "
			
			+System.getProperty("line.separator")+
			
			System.getProperty("line.separator")+
			
			"받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용
			
			
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message,
						true, "UTF-8");

				messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
				messageHelper.setTo(tomail); // 받는사람 이메일
				messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
				messageHelper.setText(content); // 메일 내용
				
				mailSender.send(message);
			} catch (Exception e) {
				System.out.println(e);
			}
			
			ModelAndView mv = new ModelAndView();	//ModelAndView로 보낼 페이지를 지정하고, 보낼 값을 지정한다.
			mv.setViewName("/member/email_injeung"); 	//뷰의이름
			mv.addObject("dice", dice);
			mv.addObject("e_mail", e_mail);
			
			System.out.println("mv : "+mv);

			response_email.setContentType("text/html; charset=UTF-8");
            PrintWriter out_email = response_email.getWriter();
            out_email.println("<script>alert('이메일이 발송되었습니다. 인증번호를 입력해주세요.');</script>");
            out_email.flush();
			
			
			return mv;
			
		}
	
	//이메일 인증 페이지 맵핑 메소드
	@RequestMapping("/member/email.do")
	public String email() {
		return "member/email";
	}
	
	
	//이메일로 받은 인증번호를 입력하고 전송 버튼을 누르면 맵핑되는 메소드.
	//내가 입력한 인증번호와 메일로 입력한 인증번호가 맞는지 확인해서 맞으면 회원가입 페이지로 넘어가고,
	//틀리면 다시 원래 페이지로 돌아오는 메소드
	@RequestMapping(value = "/member/join_injeung.do{dice},{e_mail}", method = RequestMethod.POST)
	public ModelAndView join_injeung(String email_injeung, @PathVariable String dice, @PathVariable String e_mail, HttpServletResponse response_equals) throws IOException {

		
		
		
		System.out.println("마지막 : email_injeung : "+email_injeung);
		
		System.out.println("마지막 : dice : "+dice);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/member/join.do");
		
		mv.addObject("e_mail",e_mail);
		
		if (email_injeung.equals(dice)) {
			
			//인증번호가 일치할 경우 인증번호가 맞다는 창을 출력하고 회원가입창으로 이동함
			
			
			
			mv.setViewName("member/join");
			
			mv.addObject("e_mail",e_mail);
			
			//만약 인증번호가 같다면 이메일을 회원가입 페이지로 같이 넘겨서 이메일을
			//한번더 입력할 필요가 없게 한다.
			
			response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('인증번호가 일치하였습니다. 회원가입창으로 이동합니다.');</script>");
            out_equals.flush();
	
			return mv;
			
			
		}else if (email_injeung != dice) {
			
			
			ModelAndView mv2 = new ModelAndView(); 
			
			mv2.setViewName("member/email_injeung");
			
			response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('인증번호가 일치하지않습니다. 인증번호를 다시 입력해주세요.'); history.go(-1);</script>");
            out_equals.flush();
			
	
			return mv2;
			
		}	
	
		return mv;
		
	}
	
	
	
	// mailSending 코드 (메인페이지에서 메일을 보낼때 맵핑되는 메소드)
			@RequestMapping(value = "e_mailForm.do" , method=RequestMethod.POST )
			public ModelAndView main_mailSending(HttpServletRequest request, String sender_front, String sender_back, 
			String recipient_front, String recipient_back, String title, String text, HttpServletResponse response_email) throws IOException {
				
		
				String setfrom = request.getParameter("sender_front")+request.getParameter("sender_back");			//보내는 사람 이메일
				String tomail = request.getParameter("recipient_front")+request.getParameter("recipient_back"); 	// 받는 사람 이메일
				String mail_title = request.getParameter("title"); 													// 제목
				String content = request.getParameter("text");														//이메일 내용
				
				System.out.println(setfrom); 		//값이 잘 담겼는지 테스트
				System.out.println(tomail);			
				System.out.println(mail_title);
				System.out.println(content);
				
				
				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message,
							true, "UTF-8");

					messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
					messageHelper.setTo(tomail); // 받는사람 이메일
					messageHelper.setSubject(mail_title); // 메일제목은 생략이 가능하다
					messageHelper.setText(content); // 메일 내용
					
					mailSender.send(message);
				} catch (Exception e) {
					System.out.println(e);
				}
				
				ModelAndView mv = new ModelAndView();	//ModelAndView로 보낼 페이지를 지정하고, 보낼 값을 지정한다.
				mv.setViewName("home"); 	//뷰의이름

				response_email.setContentType("text/html; charset=UTF-8");
	            PrintWriter out_email = response_email.getWriter();
	            out_email.println("<script>alert('이메일이 발송되었습니다.');</script>");
	            out_email.flush();
				
				
				return mv;
				
			}
	
	
	private boolean email_injeung(java.lang.String dice) {
		// TODO Auto-generated method stub
		return false;
	}

	private int Integer(java.lang.String string2) {
		// TODO Auto-generated method stub
		return 0;
	}

		//회원가입 페이지 맵핑 메소드
		@RequestMapping("/member/join.do")
		public String join() {
			return "member/join";
		}
	
	
	//회원가입 정보를 입력후 회원가입 버튼을 누르면 맵핑되는 메소드
	//여러개의 값들을 담아야 하므로 map에 회원의 정보들을 저장해 놓는다.
	@RequestMapping("/member/join_check.do{e_mail}")
	public ModelAndView joincheck(String user_id, String member_pass, @PathVariable String e_mail, Model model, HttpServletRequest request) {

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
		map.put("member_pass", dto.getMember_pass());
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
	
	
	//게시판에서 아이디 찾기 페이지로 이동 
	@RequestMapping("/board/find.user_id.do")
	public String board_finduser_id() {
		return "member/find_user_id";
	}
	
	
	//게시판에서 비밀번호 찾기 페이지로 이동
	@RequestMapping("/board/find.member_pass.do")
	public String board_findmember_pass() {
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
	
		//비밀번호 찾기 처리 (1) 이메일 발송
		@RequestMapping(value = "find_pass.do", method = RequestMethod.POST)
		public ModelAndView find_pass(HttpServletRequest request, String user_id, String e_mail,
				HttpServletResponse response_email) throws IOException{
			
			//랜덤한 난수 (인증번호)를 생성해서 이메일로 보내고 그 인증번호를 입력하면 비밀번호를 변경할 수 있는 페이지로 이동시킴
			
			Random r = new Random();
			int dice = r.nextInt(157211)+48271;
			
			String setfrom = "dlgkstjq623@gmail.com";
			String tomail = request.getParameter("e_mail");	//받는 사람의 이메일
			String title = "비밀번호 찾기 인증 이메일 입니다.";	//제목
			String content =
			
					System.getProperty("line.separator")+
					
					System.getProperty("line.separator")+
							
					"안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다"
					
					+System.getProperty("line.separator")+
					
					System.getProperty("line.separator")+
			
					"비밀번호 찾기 인증번호는 " +dice+ " 입니다. "
					
					+System.getProperty("line.separator")+
					
					System.getProperty("line.separator")+
					
					"받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용
			
			try {

				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
				messageHelper.setTo(tomail); // 받는사람 이메일
				messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
				messageHelper.setText(content); // 메일 내용
				
				mailSender.send(message);
		
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
			ModelAndView mv = new ModelAndView();	//ModelAndView로 보낼 페이지를 지정하고, 보낼 값을 지정한다.
			mv.setViewName("/member/pass_email"); 	//뷰의이름
			mv.addObject("dice", dice);
			mv.addObject("e_mail", e_mail);
			
			System.out.println("mv : "+mv);

			response_email.setContentType("text/html; charset=UTF-8");
            PrintWriter out_email = response_email.getWriter();
            out_email.println("<script>alert('이메일이 발송되었습니다. 인증번호를 입력해주세요.');</script>");
            out_email.flush();
			
			
			return mv;
			
		}
		
		
		//인증번호를 입력한 후에 확인 버튼을 누르면 자료가 넘어오는 컨트롤러
		@RequestMapping(value = "pass_injeung.do{dice},{e_mail}", method = RequestMethod.POST)
			public ModelAndView pass_injeung(String pass_injeung, @PathVariable String dice, @PathVariable String e_mail, 
					HttpServletResponse response_equals) throws IOException{
			
			System.out.println("마지막 : pass_injeung : "+pass_injeung);
			
			System.out.println("마지막 : dice : "+dice);
			
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("/member/pass_change");
			
			mv.addObject("e_mail",e_mail);
			
			if (pass_injeung.equals(dice)) {
				
				//인증번호가 일치할 경우 인증번호가 맞다는 창을 출력하고 비밀번호 변경창으로 이동시킨다
			
				mv.setViewName("member/pass_change");
				
				mv.addObject("e_mail",e_mail);
				
				//만약 인증번호가 같다면 이메일을 비밀번호 변경 페이지로 넘기고, 활용할 수 있도록 한다.
				
				response_equals.setContentType("text/html; charset=UTF-8");
	            PrintWriter out_equals = response_equals.getWriter();
	            out_equals.println("<script>alert('인증번호가 일치하였습니다. 비밀번호 변경창으로 이동합니다.');</script>");
	            out_equals.flush();
		
				return mv;
				
				
			}else if (pass_injeung != dice) {
				
				
				ModelAndView mv2 = new ModelAndView(); 
				
				mv2.setViewName("member/pass_email");
				
				response_equals.setContentType("text/html; charset=UTF-8");
	            PrintWriter out_equals = response_equals.getWriter();
	            out_equals.println("<script>alert('인증번호가 일치하지않습니다. 인증번호를 다시 입력해주세요.'); history.go(-1);</script>");
	            out_equals.flush();
				
		
				return mv2;
				
			}	
		
			return mv;
			
		}
		
		
		
		//변경할 비밀번호를 입력한 후에 확인 버튼을 누르면 넘어오는 컨트롤러
		@RequestMapping(value = "pass_change.do{e_mail}", method = RequestMethod.POST)
		public ModelAndView pass_change(@PathVariable String e_mail, HttpServletRequest request, MemberDTO dto, HttpServletResponse pass) throws Exception{
					
		String member_pass = request.getParameter("member_pass");
					
		String e_mail1 = e_mail;
					
		dto.setE_mail(e_mail1);
		dto.setMember_pass(member_pass);
		
		//값을 여러개 담아야 하므로 해쉬맵을 사용해서 값을 저장함
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("e_mail", dto.getE_mail());
		map.put("member_pass", dto.getMember_pass());
		
		memberservice.pass_change(map,dto);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/find_pass_result");
		
		return mv;
					
		}
				
			
			
	/*
	 * ModelAndView mav = new ModelAndView(); MemberDTO dto = new MemberDTO();
	 * 
	 * dto.setUser_id(user_id); dto.setE_mail(e_mail);
	 * 
	 * String member_pass = memberservice.find_passCheck(dto);
	 * 
	 * if(member_pass != null) { mav.setViewName("member/find_pass_result");
	 * mav.addObject("member_pass", member_pass);
	 * 
	 * }else { //비밀번호 찾기 실패 mav.setViewName("member/find_member_pass"); //뷰에 전달할 값
	 * mav.addObject("message", "회원가입된 회원의 아이디 혹은 이메일이 아닙니다."); } return mav; }
	 */
		
		
		
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
		
		
		
		//소셜 로그인을 한 후에 소셜 로그인한 회원의 정보를 데이터베이스에 저장하기 위한 메소드
		//view에서 회원인증 버튼을 누르면 맵핑되는 메소드 이다.
		@RequestMapping("authentication.do")
		public String authentication (HttpSession session) throws Exception{
			
			MemberDTO dto =  new MemberDTO();
			
			//파라미터는 오브젝트 타입으로 전송되므로 String 타입으로 타입변환을 해준후에 변수에 저장을 해야한다)
			if (session.getAttribute("navere_mail") != null && session.getAttribute("navername") != null) {
				
			String e_mail = (String)session.getAttribute("navere_mail");
			String user_id = (String)session.getAttribute("navername");
				
			dto.setE_mail(e_mail);
			dto.setUser_id(user_id);
			
			}
			
			
			if (session.getAttribute("kakaoe_mail") != null && session.getAttribute("kakaonickname") != null) {
				
			String e_mail = (String)session.getAttribute("kakaoe_mail");
			String user_id = (String)session.getAttribute("kakaonickname");
					
			dto.setE_mail(e_mail);
			dto.setUser_id(user_id);
				
			}
			

			if (session.getAttribute("facebooke_mail") != null && session.getAttribute("facebookname") != null) {
				
				String e_mail = (String)session.getAttribute("facebooke_mail");
				String user_id = (String)session.getAttribute("facebookname");
						
				dto.setE_mail(e_mail);
				dto.setUser_id(user_id);
					
			}
			
			
			memberservice.authentication(dto);
			
			
			System.out.println("<script>alert('회원인증이 완료되었습니다. 다른 기능들을 정상적으로 사용하실 수 있습니다.');</script>");
			
			
			return "home";
			
		}
	
}