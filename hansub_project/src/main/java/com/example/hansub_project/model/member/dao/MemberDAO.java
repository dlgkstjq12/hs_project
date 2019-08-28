package com.example.hansub_project.model.member.dao;

import java.util.HashMap;
import java.util.Map;

import com.example.hansub_project.model.member.dto.MemberDTO;

public interface MemberDAO {

	public void join(Map<String, Object>map,MemberDTO dto); 	//회원가입 관련
	
	public boolean loginCheck(MemberDTO dto);		//로그인 관련
	
	public String find_idCheck(MemberDTO dto);		//아이디 찾기
	
	public String find_passCheck(MemberDTO dto);	//비밀번호 찾기

	public void authentication(MemberDTO dto);		//소셜 로그인 회원인증 관련 메소드

	public void pass_change(Map<String, Object> map, MemberDTO dto)throws Exception;	//비밀번호 변경
	
}
