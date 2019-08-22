package com.example.hansub_project.service.admin;

import javax.servlet.http.HttpSession;

import com.example.hansub_project.model.admin.dto.AdminDTO;
import com.example.hansub_project.model.member.dto.MemberDTO;

public interface AdminService {

	boolean loginCheck(AdminDTO dto, HttpSession session) throws Exception;	//관리자 로그인을 체크하는 메소드

	
	void admin_member_forced_evictionCheck(MemberDTO dto) throws Exception; //강제탈퇴 시킬때 해당 회원이 있는지 체크하는 메소드

	
}
