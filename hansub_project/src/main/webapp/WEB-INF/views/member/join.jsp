<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/menu.jsp"%><br>


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
					<form action="join_check.do${e_mail}" method="post">
					<center>
						<div>
							아이디 : <input type="text" name="user_id" placeholder="  ID를 입력하세요. ">
						</div>
						<br>
						<div>
							비밀번호 : <input type="password" name="member_pass"
								placeholder="  비밀번호를 입력하세요. ">
						</div>
						<br>
						<div>
							인증받은 이메일 : ${e_mail}
						</div>						
						
						<!-- 이메일은 인증받은 이메일을 사용해야 하므로 컨트롤러에서 이메일을 가져와서 사용함 -->
						<!-- 가져온후에 다시 컨트롤러로 넘긴후에 db에 저장하는 식으로 진행 -->
						
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