package com.example.hansub_project.model.board.dto;

import java.sql.Date;

public class MemberBoardReplyDTO {
	
	private int rno;		//댓글 번호
	private int member_bno;	//게시글의 번호
	private String r_content; //댓글 내용
	private String user_id; //댓글 작성자 id
	private Date reg_date; //java.util.Date, 작성일자
	private Date join_date;	//회원의 가입 일자
	private int recommend;	//추천수 
	
	
	public int getRno() {
		return rno;
	}
	
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getMember_bno() {
		return member_bno;
	}
	public void setMember_bno(int member_bno) {
		this.member_bno = member_bno;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	

	@Override
	public String toString() {
		return "MemberBoardReplyDTO [rno=" + rno + ", member_bno=" + member_bno + ", r_content=" + r_content
				+ ", user_id=" + user_id + ", reg_date=" + reg_date + ", join_date=" + join_date + ", recommend="
				+ recommend + "]";
	}
	
}
