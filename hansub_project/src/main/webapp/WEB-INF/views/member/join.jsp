<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<br><%@ include file="../include/header.jsp"%>
<br><%@ include file="../include/menu.jsp"%><br>


<!-- 회원가입 페이지 -->


<body>
<center>
<table border="1" width="300" height="300">
	
		<br> <br>
		<center>
		<span style="color: green; font-weight: bold;">회원가입</span> <br> <br>

		
		<div style="text-align:center;">
			<tr>		
				<td>
					<form action="join.check.do" method="post">
					<center>
						<div>
							아이디 : <input type="text" name="user_id" placeholder="ID를 입력하세요.">
						</div>
						<br>
						<div>
							비밀번호 : <input type="password" name="member_pass"
								placeholder="비밀번호를 입력하세요.">
						</div>
						<br>
						<div>
							이메일 : <input type="email" name="e_mail"
								placeholder="이메일주소를 입력하세요.">
						</div>
						<br> <br>
						<button type="submit" name="submit">회원가입</button>
					</center>
						</div>
					</td>
				</tr>
			</table>
			</center>
		</form>
		</center>

</body>
</html>