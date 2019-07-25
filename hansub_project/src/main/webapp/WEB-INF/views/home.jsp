<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
</head>
<%@ include file="include/header.jsp"%>

<body>
<center>
<a href="${path}">메인페이지</a> ㅣ
<a href="${path}/member/join.do">회원가입</a> ㅣ
<a href="${path}/member/member_board.do">회원게시판</a> ㅣ
<a href="${path}/member/member_best_board.do">베스트게시판</a> ㅣ
<a href="${path}/member/admin_board.do">공지사항</a> ㅣ
<br>
<br>
</center>

<%@ include file="member/login.jsp"%>

<!-- 네이버 아이디로 로그인하기 버튼 -->
<div id = "naver_id_login"></div>

</body>
</html>