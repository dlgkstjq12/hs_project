<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<br><%@ include file="header.jsp"%>
<br><%@ include file="menu.jsp"%><br>
<body>

<!-- 회원정보에 없는 이메일을 입력할 시에 출력되는 경고창 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(function(){
		var responseMessage = "<c:out value="${message}" />";
		if (responseMessage != ""){
			alert(responseMessage)
		}
	})
</script>


<center>
		<center>
		<br>
		<br>
		<br>
		<span style="color: green; font-weight: bold;">아이디 찾기</span> <br> <br>
		</center>
		
<form action = "find_id.do" method = "post">
<center>
이메일 : <input type="text" name="e_mail" placeholder="이메일을 입력하세요.">
<button type = "submit" name = "submit" >확인</button>
</center>
</form>




</body>
</html>