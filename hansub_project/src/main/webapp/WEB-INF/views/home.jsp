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
		<a href="${path}">메인페이지</a> ㅣ <a href="${path}/member/join.do">회원가입</a>
		ㅣ <a href="${path}/member/member_board.do">회원게시판</a> ㅣ <a
			href="${path}/member/member_best_board.do">베스트게시판</a> ㅣ <a
			href="${path}/member/admin_board.do">공지사항</a> ㅣ <br> <br>
	</center>
	

	
	<!-- url 파라미터로 받은 로그인한 아이디 값이 있을시에는 "name+방문을 환영한다"고 출력이 되고, null값일 때에는 "guest님 방문을 환영합니다" 메시지가 출력되도록 한다.-->
	
	<%
		String name = request.getParameter("name");
		
		
		if (name == null) {
	%>
		guest님 방문을 환영합니다.
	
	<%@ include file="member/login.jsp"%>
	<%
		} else {
	%>
	<%=name%>님 방문을 환영합니다.
	
	<form action ="logout.do" method = "post">
	<button type = "submit" name = "submit" >로그아웃</button>
	</form>
	
	<%
		};
		
	%>

</body>
</html>