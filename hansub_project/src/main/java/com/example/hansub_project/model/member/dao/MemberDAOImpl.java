package com.example.hansub_project.model.member.dao;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.Session;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.hansub_project.model.member.dto.MemberDTO;


@Repository
public class MemberDAOImpl implements MemberDAO {

	
	@Inject
	SqlSession sqlSession;
	
	
	//회원가입 관련 메소드
	@Override
	public void join(Map<String, Object>map, MemberDTO dto) {

		map.get("user_id");
		map.get("member_pass");
		map.get("e_mail");
		
		sqlSession.insert("member.insertUser",map);		
	}
	
	
	//로그인관련 메소드
	@Override
	public boolean loginCheck(MemberDTO dto) {
		String name
			=sqlSession.selectOne("member.login_check", dto);
		
		//조건식 ? true일때의 값 : false일때의 값
		return (name==null) ? false : true;
	}

	
	//아이디 찾기 관련 메소드
	@Override
	public String find_idCheck(MemberDTO dto) {
		String id = sqlSession.selectOne("member.find_id_check", dto);
		return id;
		
	}

	
	//비밀번호 찾기 관련 메소드
	@Override
	public String find_passCheck(MemberDTO dto) {
		String pass = sqlSession.selectOne("member.find_pass_check", dto);
		return pass;
	}

	
	//회원 인증 관련 메소드
	//버튼을 클릭한 회원의 정보를 회원 테이블에 저장해서 사용할 수 있게 함
	@Override
	public void authentication(MemberDTO dto) {
		
		sqlSession.insert("member.authentication", dto);
		
	}


	@Override
	public void pass_change(Map<String, Object> map, MemberDTO dto)throws Exception{
		
		map.get("member_pass");
		map.get("e_mail");

		sqlSession.update("member.pass_change", map);
	}
	
}
