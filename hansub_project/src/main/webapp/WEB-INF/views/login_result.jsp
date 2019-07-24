<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="header.jsp"%>
</head>
<%@ include file="menu.jsp"%>
<body>
<br>
<br>
<br>
<br>
${user_id}님 로그인을 환영합니다!!

<form action ="logout.do" method = "post">
<button type = "submit" name = "submit" >로그아웃</button>


</body>
</html>