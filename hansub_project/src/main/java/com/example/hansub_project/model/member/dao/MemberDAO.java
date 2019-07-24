package com.example.hansub_project.model.member.dao;

import java.util.HashMap;
import java.util.Map;

import com.example.hansub_project.model.member.dto.MemberDTO;

public interface MemberDAO {

	public void join(Map<String, Object>map,MemberDTO dto);
	public boolean loginCheck(MemberDTO dto);
	public String find_idCheck(MemberDTO dto);
	public String find_passCheck(MemberDTO dto);
	
}
