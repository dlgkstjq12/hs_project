<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/menu.jsp"%><br>
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
		<br>
		<br>
		<br>
		<span style="color: green; font-weight: bold;">아이디 찾기</span> <br> <br>
		</center>

<!-- 아이디를 찾는 페이지 (이메일을 입력하면 db에 연동후 db에 저장된 아이디가 출력됨) -->
<form action = "find_id.do" method = "post">
<center>
이메일 : <input type="text" name="e_mail" placeholder="이메일을 입력하세요.">
<button type = "submit" name = "submit" >확인</button>
</center>
</form>




</body>
</html>