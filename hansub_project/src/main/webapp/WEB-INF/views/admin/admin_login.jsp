<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="576736845363-o0474pib5q69qlcv6lm7o42hs6lu5u59.apps.googleusercontent.com">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name = "viewport" content = "user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,
width=device-width" />

<title>Insert title here</title>

</head>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/menu.jsp" %>


<br>
<br>
<br>
<br>
<br>

<center>
<table border="1" width="400">

<tr>
<td>
<br>
<center>
<span style="color:green; font-weight : bold;">관리자 로그인</span>
</center>

<center>
<!-- 로그인창 -->
<form action ="admin_login.do" method = "post">
<center>
<br>
-관리자 아이디- <input type = "text" name="admin_id" placeholder="  ID를 입력하세요 "><br><br>
-관리자 비밀번호- <input type = "password" name="admin_pass" placeholder="  비밀번호를 입력하세요 "><br><br>
<button type = "submit" name = "submit" >로그인</button>

<br>
<br>
<div class = "row">
	<div class="col-xs-8">
		<div class="checkbox icheck">
		<label>
			<input type = "checkbox" name = "useCookie"> 로그인유지
		</label>
		</div>
	</div>
</div>
</center>

<center>


</form>

</center>
</td>
</tr>
</table>
</center>
</body>


<!-- 로그인 실패나 성공시 메시지를 받아서 출력하는 자바스크립트 구문 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(function(){
		var responseMessage = "<c:out value="${message}" />";
		if (responseMessage != ""){
			alert(responseMessage)
		}
	})
</script>



</html>