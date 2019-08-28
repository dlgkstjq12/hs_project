<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 페이지</title>

</head>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/menu.jsp"%><br>
<body>

<!-- 비밀번호 찾기 페이지 -->
<!-- 에러메시지를 전달받는 함수 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>

	$(function(){
		var responseMessage = "<c:out value="${message}" />";
		if (responseMessage != ""){
			alert(responseMessage)
		}
	})
</script>

<!-- id와 이메일을 입력하면 컨트롤러로 맵핑 -->
<form action = "find_pass.do" method = "post">
<center>
<br>
<br>
<br>
<span style="color: green; font-weight: bold;">비밀번호 찾기</span> <br> <br>
아이디 : <input type = "text" name="user_id" placeholder="ID를 입력하세요."><br><br>
이메일 : <input type="text" name="e_mail" placeholder="이메일을 입력하세요."><br><br>
<button type = "submit" name = "submit" >확인</button>

<br>
<br>
입력하신 이메일로 인증번호가 발송됩니다. 받으신 인증번호를 입력해주세요.
</center>
</form>

</body>
</html>
