<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>



</head>
<%@ include file="include/header.jsp"%>
<%@ include file="include/menu.jsp"%>

<body>


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
  
 <html>
 	<head>
 	
 	</head>
 	<body>
 	
 	</body>
 </html>

  
  
  

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



<%@ include file="include/login.jsp"%><br>
	
	
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