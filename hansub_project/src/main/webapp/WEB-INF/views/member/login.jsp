<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="576736845363-o0474pib5q69qlcv6lm7o42hs6lu5u59.apps.googleusercontent.com">
<title>Insert title here</title>
</head>
<br><br><%@ include file="../include/header.jsp"%>






<table border="1" width="200">

<tr>
<td>
<br>
<center>
<span style="color:green; font-weight : bold;">로그인</span>
</center>


<!-- 로그인창 -->
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


<center>


<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
</head>
<body>
<br>
<!-- 네이버아이디로로그인 버튼 노출 영역 -->
<div id="naverIdLogin"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			//클라이언트 id와 콜백 url (결과페이지)
			clientId: "DphfmDygX4WFkf8nghMJ",
			callbackUrl: "http://localhost:8090/hansub_project/login_result",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			loginButton: {color: "green", type: 3, height: 40} /* 로그인 버튼의 타입을 지정 */
		}
	);
	
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
	
</script>

</center>


<!-- 구글 로그인 관련 API -->
<script src="https://apis.google.com/js/platform.js" async defer></script>
  </head>
  <body>
  <center>
    <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
    </center>
    <center>
    <script>
 
    	function signOut() {
  	  	var auth2 = gapi.auth2.getAuthInstance();
  	  	auth2.signOut().then(function(){
  		console.log('User signed out.'); 
  	  		});
  	  	auth2.disconnect();
  		}
    
      function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());
        
        //이메일을 넘기기위해 변수에 저장
        var name = profile.getEmail();
		
        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
        
        //변수의 값이 null이 아니면 name변수를 넘기고, null이면 값을 넘기지 않는다.
	if (name !== null){
		window.location.replace("http://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "/hansub_project/home?name="+name);
	} else if (name == null){
		
		window.location.replace("http://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "/hansub_project/home");
	}
	
      }
     
    </script>
</center>


</form>

<br>
<!-- 아이디 찾기 -->
<form action ="find.user_id.do">
<center>
<button>아이디 찾기</button>
</center>
</form>

<br>
<!-- 비밀번호 찾기 -->
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