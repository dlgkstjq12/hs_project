<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>



</head>
<%@ include file="include/header.jsp"%>

<body>
	<center>
		<a href="${path}">메인페이지</a> ㅣ
		 <a href="${path}/member/email.do">회원가입</a>ㅣ 
		<a href="list.do">회원게시판</a> ㅣ <a
			href="${path}/member/member_best_board.do">베스트게시판</a> ㅣ <a
			href="${path}/member/admin_board.do">공지사항</a> ㅣ <br> <br>
	</center>
	
<br>
<br>

<!-- 문자보내는 폼 -->
<form method="post" id="smsForm">
<table border = "1" align="right" width = "300" height = "200" >

<tr>
<td>
<center>
<br>
<span style="color: green; font-weight: bold;">SMS 전송 (문자보내기)</span>
 </center>
    <ul>
      <li>받는 사람 : <input type="text" name="from" placeholder=" 전화번호 입력 ( '-' 포함 )"/></li><br>
      <li>내용 : <textarea name="text" placeholder=" 보낼 내용 입력 "></textarea>    </li><br>
      <center>
      <input type="button" onclick="sendSMS('sendSms')" value="전송하기" /><br>
      </center>
    </ul>

    </td>
    </tr>
    </table>
  </form>

  <script>
    function sendSMS(pageName){

    	console.log("문자를 전송합니다.");
    	$("#smsForm").attr("action", pageName + ".do"); //위에 있는 폼태그를 컨트롤러로 전송한다.
    	$("#smsForm").submit();
    }
  </script>
  
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
 
  <!-- 이메일 보내기 폼 -->
<form action ="e_mailForm.do" method = "post">
<table border = "1" align="right" width = "450" height = "250" >

<tr>
<td>
<center>
<br>
<span style="color: green; font-weight: bold;">이메일 보내기</span>
 </center>
    <ul>
      <li>보내는 사람 : <input type="text" name="sender_front" placeholder="이메일 아이디 입력">
      <select type = "text" name = "sender_back"  >
      		
      			<option value = "@naver.com">@naver.com</option>				
      			<option value = "@hanmail.net">@hanmail.net</option>
      			<option value = "@gmail.com">@gmail.com</option>
      
      </select>
      </li>
      <br>
      
      
      <li>받는 사람 : <input type="text" name = "recipient_front" placeholder="이메일 아이디 입력">
      <select name="recipient_back" type="text" >
      
      			<option value = "@naver.com">@naver.com</option>				
      			<option value = "@hanmail.net">@hanmail.net</option>
      			<option value = "@gmail.com">@gmail.com</option>
      			<option value = "@chol.com">@chol.com</option>				
      			<option value = "@empal.com">@empal.com</option>
      			<option value = "@freechal.com">@freechal.com</option>
      			<option value = "@hanmir.com">@hanmir.com</option>				
      			<option value = "@hitel.net">@hitel.net</option>
      			<option value = "@nete.com">@nate.com</option>
      			
			
      </select>
      </li>
      <br>
      
      
      <li>제목 : <input type="text" name="title" placeholder=" 이메일의 제목 입력"/></li><br>
      <li>내용 : <textarea name="text" name = "text" placeholder=" 보낼 내용 입력 "></textarea>    </li><br>
      <center>
      <button type = "submit" name = "submit" >이메일 전송</button>
      </center>
    </ul>

    </td>
    </tr>
    </table>
  </form>

  

	<!-- url 파라미터로 받은 로그인한 아이디 값이 있을시에는 "name+방문을 환영한다"고 출력이 되고, null값일 때에는 "guest님 방문을 환영합니다" 메시지가 출력되도록 한다.-->
	
	<%
		String navername = request.getParameter("navername");
		String kakaonickname = request.getParameter("kakaonickname");
		String facebookname = request.getParameter("facebookname");
		String normalname = request.getParameter("user_id");
	%>	
		
	<%
		session.setAttribute("navername", navername);
		session.setAttribute("kakaonickname", kakaonickname);
		session.setAttribute("facebookname", facebookname);
		session.setAttribute("normalname", normalname);

		if (navername == null && kakaonickname == null && facebookname == null && normalname == null) {
	%>
	
		(guest)님 방문을 환영합니다. 	<br>
								<br>
								
		로그인을 하셔야 다른 기능을 정상적으로 이용하실 수 있습니다. <br>
		
	<%@ include file="member/login.jsp"%>
	
	<!-- 네이버 로그인이 되어있으면 출력되는 구문 -->
	<%
		} else if (navername != null){
	%>
	<%=" (네이버) "+session.getAttribute("navername")%>님 방문을 환영합니다.
	
	<form action ="naver_logout.do" method = "post">
	<button type = "submit" name = "submit" >로그아웃</button>
	</form>
	
	<!-- 카카오톡 로그인이 되어있으면 출력되는 구문 -->
	
	<%	
		} else if (kakaonickname != null){
	%>
	
	<%=" (카카오톡) "+session.getAttribute("kakaonickname")%>님 방문을 환영합니다.
	
	<form action = "kakao_logout.do" method = "post">
	<button type = "submit" name = "submit">로그아웃</button></form>
	
	
	<!-- 페이스북 로그인이 되어있으면 출력되는 구문 -->
	<%
		} else if (facebookname != null){
	%>
	
	<%=" (페이스북) "+session.getAttribute("facebookname")%>님 방문을 환영합니다.
	
	<form action = "facebook_logout.do" method = "post">
	<button type = "submit" name = "submit">로그아웃</button></form>
	
	
	<!-- (일반) 회원가입된 회원이 로그인이 되어있으면 출력되는 구문 -->
	<% 
		} else if (normalname != null){
	%>
	<%=" (일반) "+session.getAttribute("normalname")%>님 방문을 환영합니다.
	
	<form action = "logout.do" method = "post">
	<button type = "submit" name = "submit">로그아웃</button></form>
	<%
		};
	%>
	
	
</body>
</html>