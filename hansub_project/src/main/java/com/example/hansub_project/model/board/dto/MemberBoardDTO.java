package com.example.hansub_project.model.board.dto;

import java.sql.Date;

//회원 게시판 관련 dto 클래스
public class MemberBoardDTO {
	
	private int member_bno;		//게시글 번호
	private String user_id;		//작성자 아이디
	private Date reg_date;		//작성 날짜
	private int viewcnt;		//조회수
	private String title;		//제목
	private int rcnt;			//댓글 갯수
	private String content;		//댓글 내용
	private int recommend;		//추천수
	
	public int getMember_bno() {
		return member_bno;
	}
	public void setMember_bno(int member_bno) {
		this.member_bno = member_bno;
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
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRcnt() {
		return rcnt;
	}
	public void setRcnt(int rcnt) {
		this.rcnt = rcnt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	
	@Override
	public String toString() {
		return "MemberBoardDTO [member_bno=" + member_bno + ", user_id=" + user_id + ", reg_date=" + reg_date
				+ ", viewcnt=" + viewcnt + ", title=" + title + ", rcnt=" + rcnt + ", content=" + content
				+ ", recommend=" + recommend + "]";
	}

	
	
}
