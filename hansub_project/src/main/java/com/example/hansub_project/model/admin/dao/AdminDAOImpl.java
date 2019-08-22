package com.example.hansub_project.model.admin.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.hansub_project.model.admin.dto.AdminDTO;
import com.example.hansub_project.model.member.dto.MemberDTO;


@Repository	//dao 관련 빈
public class AdminDAOImpl implements AdminDAO {

	
	@Inject		//sql쿼리를 사용하기 위해서 의존성을 주입함
	SqlSession sqlSession;
	
	
	//로그인 체크
	//id가 null이면 false를 리턴하고 값이 있으면 true를 리턴한다.
	@Override
	public boolean loginCheck(AdminDTO dto) throws Exception{
		
		String name = sqlSession.selectOne("admin.login_check", dto);
		
		//조건식 ? true일때의 값 : false일때의 값
		return (name==null) ? false : true;
		
	}

	
	//회원 강제탈퇴 관련 메소드 구현
	@Override
	public void admin_member_forced_evictionCheck(MemberDTO dto) throws Exception{
		
			sqlSession.delete("admin.admin_member_forced_evictionCheck", dto);

	}


	
}
