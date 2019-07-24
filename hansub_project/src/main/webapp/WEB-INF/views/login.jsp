<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<br><%@ include file="header.jsp"%>

<table border="1" width="200">

<tr>
<td>
<br>
<center>
<span style="color:green; font-weight : bold;">로그인</span>
</center>

<form action ="login.do" method = "post">
<center>
<br>
<input type = "text" name="user_id" placeholder="ID를 입력하세요"><br><br>
<input type = "password" name="member_pass" placeholder="비밀번호를 입력하세요"><br><br>
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
</form>
<br>
<form action ="find.user_id.do">
<center>
<button>아이디 찾기</button>
</center>
</form>

<br>
<form action ="find.member_pass.do">
<center>
<button>비밀번호찾기</button>
</center>
</form>
<br>

</td>
</tr>

</table>



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










</body>
</html>