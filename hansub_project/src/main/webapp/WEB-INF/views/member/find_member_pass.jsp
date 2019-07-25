<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 페이지</title>
<br><%@ include file="../include/header.jsp"%>
<br><%@ include file="../include/menu.jsp"%><br>
</head>
<body>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(function(){
		var responseMessage = "<c:out value="${message}" />";
		if (responseMessage != ""){
			alert(responseMessage)
		}
	})
</script>


<form action = "find_pass.do" method = "post">
<center>
<br>
<br>
<br>
<span style="color: green; font-weight: bold;">비밀번호 찾기</span> <br> <br>
아이디 : <input type = "text" name="user_id" placeholder="ID를 입력하세요."><br><br>
이메일 : <input type="text" name="e_mail" placeholder="이메일을 입력하세요."><br><br>
<button type = "submit" name = "submit" >확인</button>
</center>
</form>

</body>
</html>
