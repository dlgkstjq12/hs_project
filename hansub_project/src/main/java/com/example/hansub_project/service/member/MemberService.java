package com.example.hansub_project.service.member;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.hansub_project.model.member.dto.MemberDTO;


public interface MemberService {
		
	public void join (Map<String, Object>map,MemberDTO dto);
	public boolean loginCheck(MemberDTO dto, HttpSession session);
	public String find_idCheck(MemberDTO dto);
	public String find_passCheck(MemberDTO dto);
	
}
