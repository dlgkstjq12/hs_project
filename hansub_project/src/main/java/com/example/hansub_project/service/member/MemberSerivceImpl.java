package com.example.hansub_project.service.member;

import java.io.PrintWriter;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.hansub_project.model.member.dao.MemberDAO;
import com.example.hansub_project.model.member.dto.MemberDTO;

@Service
public class MemberSerivceImpl implements MemberService {

	
	@Inject
	MemberDAO memberdao;
	
	
	@Override
	public void join(Map<String, Object>map,MemberDTO dto) {
		memberdao.join(map,dto);

	}


	@Override
	public boolean loginCheck(MemberDTO dto, HttpSession session) {
		
		boolean result = memberdao.loginCheck(dto);
		if(result) {	//로그인 성공
			session.setAttribute("user_id", dto.getUser_id());
			session.setAttribute("member_pass", dto.getMember_pass());
			System.out.println(session.getAttribute("user_id"));
			System.out.println(session.getAttribute("member_pass"));
		}
		
		return result;
	}

	//아이디 찾기
	@Override
	public String find_idCheck(MemberDTO dto) {
		String id = memberdao.find_idCheck(dto);
		
		return id;
	}

	//비밀번호 찾기
	@Override
	public String find_passCheck(MemberDTO dto) {
		String pass = memberdao.find_passCheck(dto);
		return pass;
	}
	
}