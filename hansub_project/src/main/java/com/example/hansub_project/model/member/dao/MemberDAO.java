package com.example.hansub_project.model.member.dao;

import java.util.HashMap;
import java.util.Map;

import com.example.hansub_project.model.member.dto.MemberDTO;

public interface MemberDAO {

	public void join(Map<String, Object>map,MemberDTO dto); 	//회원가입 관련
	public boolean loginCheck(MemberDTO dto);		//로그인 관련
	public String find_idCheck(MemberDTO dto);		//아이디 찾기
	public String find_passCheck(MemberDTO dto);	//비밀번호 찾기
	
}
