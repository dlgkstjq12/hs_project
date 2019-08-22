package com.example.hansub_project.model.admin.dao;

import com.example.hansub_project.model.admin.dto.AdminDTO;
import com.example.hansub_project.model.member.dto.MemberDTO;

public interface AdminDAO {

	boolean loginCheck(AdminDTO dto) throws Exception;	//로그인을 체크하는 메소드


	void admin_member_forced_evictionCheck(MemberDTO dto) throws Exception;	//회원 강제탈퇴 관련 메소드

}
