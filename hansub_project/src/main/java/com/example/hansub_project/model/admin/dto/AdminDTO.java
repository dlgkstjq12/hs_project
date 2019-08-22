package com.example.hansub_project.model.admin.dto;

import java.util.Date;

public class AdminDTO {
	
	
	private String admin_id;	//아이디
	private String admin_pass;	//비밀번호
	private String e_mail;	//이메일
	private Date reg_date;	//등록일자
	
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}
	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
		
	@Override
	public String toString() {
		return "AdminDTO [admin_id=" + admin_id + ", admin_pass=" + admin_pass + ", e_mail=" + e_mail + ", reg_date="
				+ reg_date + "]";
	}
	

}
