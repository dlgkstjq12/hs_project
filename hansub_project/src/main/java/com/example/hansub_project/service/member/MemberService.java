package com.example.hansub_project.service.member;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.hansub_project.model.member.dto.MemberDTO;


public interface MemberService {
		
	public void join (Map<String, Object>map,MemberDTO dto); //회원가입 관련
	public boolean loginCheck(MemberDTO dto, HttpSession session);	//로그인 관련
	public String find_idCheck(MemberDTO dto);	//아이디 찾기 관련
	public String find_passCheck(MemberDTO dto);	//비밀번호 찾기 관련
	public void authentication(MemberDTO dto);		//회원 인증관련 메소드
	public void pass_change(Map<String, Object> map, MemberDTO dto)throws Exception;	//비밀번호 변경
	


}
