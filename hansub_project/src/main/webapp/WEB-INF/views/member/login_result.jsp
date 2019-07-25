<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<br><%@ include file="../include/header.jsp"%>
<br><%@ include file="../include/menu.jsp"%><br>
<body>
<br>
<br>
<br>
<br>

<form action ="logout.do" method = "post">
${user_id}님 로그인을 환영합니다!!
<button type = "submit" name = "submit" >로그아웃</button>
</form>




<script type="text/javascript">
		var naver_id_login = new naver_id_login("DphfmDygX4WFkf8nghMJ", "http://localhost:8090/hansub_project/login_result"); // 역시 마찬가지로 'localhost'가 포함된 CallBack URL
		
		// 접근 토큰 값 출력
		alert(naver_id_login.oauthParams.access_token);
		
		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");
		
		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		function naverSignInCallback() {
			alert(naver_id_login.getProfileData('email'));
			alert(naver_id_login.getProfileData('nickname'));
			alert(naver_id_login.getProfileData('age'));
		}
		
	</script>



</body>
</html>