<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="header.jsp"%>


<body>

<%
String navername = request.getParameter("navername");
String kakaonickname = request.getParameter("kakaonickname");
String facebookname = request.getParameter("facebookname");
String normalname = request.getParameter("user_id");
%>	


<%
session.setAttribute("navername", navername);
session.setAttribute("kakaonickname", kakaonickname);
session.setAttribute("facebookname", facebookname);
session.setAttribute("normalname", normalname);

if (navername == null && kakaonickname == null && facebookname == null && normalname == null) {
%>

(guest)님 방문을 환영합니다. 	<br>
								<br>
								
로그인을 하셔야 다른 기능을 정상적으로 이용하실 수 있습니다. <br>
		
	<%@ include file="../member/login_form.jsp"%><br>
	
	<!-- 네이버 로그인이 되어있으면 출력되는 구문 -->
	<%
		} else if (navername != null){
	%>
	<%=" (네이버) "+session.getAttribute("navername")%>님 방문을 환영합니다.
	
	<form action ="naver_logout.do" method = "post">
	<button type = "submit" name = "submit" >로그아웃</button>
	</form>
	
	<!-- 카카오톡 로그인이 되어있으면 출력되는 구문 -->
	
	<%	
		} else if (kakaonickname != null){
	%>
	
	<%=" (카카오톡) "+session.getAttribute("kakaonickname")%>님 방문을 환영합니다.
	
	<form action = "kakao_logout.do" method = "post">
	<button type = "submit" name = "submit">로그아웃</button></form>
	
	
	<!-- 페이스북 로그인이 되어있으면 출력되는 구문 -->
	<%
		} else if (facebookname != null){
	%>
	
	<%=" (페이스북) "+session.getAttribute("facebookname")%>님 방문을 환영합니다.
	
	<form action = "facebook_logout.do" method = "post">
	<button type = "submit" name = "submit">로그아웃</button></form>
	
	
	<!-- (일반) 회원가입된 회원이 로그인이 되어있으면 출력되는 구문 -->
	<% 
		} else if (normalname != null){
	%>
	(일반) ${sessionScope.user_id}님 방문을 환영합니다.
	
	<form action = "logout.do" method = "post">
	<button type = "submit" name = "submit">로그아웃</button></form>
	<%
		};
	%>
	<br>

</body>
</html>