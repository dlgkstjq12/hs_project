<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- views/include/menu.jsp -->
 <%@ include file="header.jsp"%>
<center>

<!-- 다른 기능들 링크가 걸려있는 메뉴 페이지 -->

ㅣ  <a href="${path}/home">메인페이지</a> ㅣ

<!-- 어떠한 아이디로든 로그인되지 않았을 경우에만 회원가입 링크를 출력시킨다. -->
<c:if test = "${sessionScope.user_id == null and sessionScope.navername == null and sessionScope.kakaonickname == null and sessionScope.facebookname == null and sessionScope.admin_id == null}">
<a href="${path}/member/email.do">회원가입</a> ㅣ
</c:if>



<a href="${path}/board/list.do">회원 게시판</a> ㅣ

<!-- 관리자가 로그인 하지 않았을 경우에만 로그인 링크를 출력시킴 -->
<c:if test = "${sessionScope.user_id == null and sessionScope.navername == null and sessionScope.kakaonickname == null and sessionScope.facebookname == null and sessionScope.admin_id == null}">
<a href="${path}/admin/admin_login_view.do">관리자 로그인</a> ㅣ	
</c:if>


<a href="${path}/board/admin_board_list.do">공지사항</a> ㅣ
<br>
<br>

<c:if test = "${sessionScope.admin_id != null}">
<table border="1" whdth="100">
<tr>
관리자 메뉴 : ㅣ <a href="${path}/admin/admin_member_forced_eviction_view.do">회원 강제 탈퇴</a> ㅣ	
</tr>
</table>
</c:if>

</center>
 
 