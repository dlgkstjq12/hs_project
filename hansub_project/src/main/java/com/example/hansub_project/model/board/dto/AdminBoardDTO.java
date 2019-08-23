package com.example.hansub_project.model.board.dto;

import java.sql.Date;

public class AdminBoardDTO {
	
	private int bno;		//게시글 번호
	private String admin_id;	//관리자 아이디
	private Date reg_date;		//작성 날짜
	private int viewcnt;		//조회수
	private String title;		//제목
	private int rcnt;			//댓글 갯수
	private String content;		//댓글 내용
	
	
	
	public int getBno() {
		return bno;
	}
	
	
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	
	public String getAdmin_id() {
		return admin_id;
	}
	
	
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
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

	@Override
	public String toString() {
		return "AdminBoardDTO [bno=" + bno + ", admin_id=" + admin_id + ", reg_date=" + reg_date + ", viewcnt="
				+ viewcnt + ", title=" + title + ", rcnt=" + rcnt + ", content=" + content + "]";
	}
	
	
}
