<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="../include/header.jsp"%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>

$(function(){

	//댓글 수정 버튼
	$("#btn_reply_Update").click(function(){
    if(confirm("수정 하시겠습니까?")){
    	
    //수정하는데 필요한 정보들, 댓글 번호, 글 내용, 작성자 아이디, 게시글 번호를 변수에 저장한다.
    	var rno = $("#rno").val();
    	var r_content = $("textarea#r_content").text();
    	var user_id = $("#user_id").val();
    	var member_bno = $("#member_bno").val();
    	
    //게시글 세부 페이지로 포워딩을 하기위해 페이지 관련 값들을 변수에 저장해서 컨트롤러로 보낸다.
		var curPage = $("#curPage").val();
		var search_option = $("#search_option").val();
		var keyword = $("#keyword").val();
		
		//페이지 관련 값들과 댓글 수정에 필요한 값들을 url로 전송한다.
    	document.form1.action="reply_update.do?rno="+rno+"&r_content="+encodeURI(r_content)+"&user_id="+user_id+"&member_bno="+member_bno+"&curPage="+curPage+"&search_option="+search_option+"&keyword="+keyword;
    	document.form1.submit();
    	
    	
    	alert("댓글이 수정되었습니다.")
    			}
		});

	
	//댓글 삭제 버튼
	$("#btn_reply_Delete").click(function(){
		
		if(confirm("삭제 하시겠습니까?")){
		
		//댓글 삭제를 하기위해 댓글 번호, 글 번호, 댓글 내용, 그리고 게시글 세부 페이지로 포워딩 하기 위해 페이지 관련 값들을 변수에 저장한다.
			var rno = $("#rno").val();
			var member_bno = $("#member_bno").val();
			var content = $("textarea#r_content").text();
			var curPage = $("#curPage").val();
			var search_option = $("#search_option").val();
			var keyword = $("#keyword").val();
			
			
			//url로 삭제에 필요한 변수들을 보낸다.
			document.form1.action="reply_delete.do?rno="+rno+"&member_bno="+member_bno+"&curPage="+curPage+"&search_option="+search_option+"&keyword="+keyword;
		    
			document.form1.submit();
		    
			alert("댓글이 삭제되었습니다.")
	    	
		}
	});
});

</script>



<body>
<!-- 배열이 비어있지 않으면 참을 출력함. (다시말해서 배열에 값들이 있으면 댓글 리스트를 출력한다.) -->



<c:if test = "${not empty map.list}">
<h2>댓글 리스트</h2>
<table border = "1" width = "800px" align = "left">

<c:forEach var = "row" items = "${map.list}">


<tr>
<td><br><br>

닉네임 : ${row.user_id}    작성일자 : ${row.reg_date}   댓글번호 : ${row.rno }<br><br><br>

${row.r_content}

<!-- 폼태그 안에 위쪽에 있는 자바스크립트 구문에 필요한 값들을 노출시키지 않게 하기 위해 hidden타입으로 값들을 전달한다. -->
<form method = "POST" id = "form1">

<input type = "hidden" id = "rno" name = "rno" value = "${row.rno}">
<input type = "hidden" id = "user_id" name = "user_id" value = "${row.user_id}">
<input type = "hidden" id = "member_bno" name = "member_bno" value = "${row.member_bno}">
<input type = "hidden" id = "curPage" name = "curPage" value = "${curPage}">
<input type = "hidden" id = "search_option" name = "search_option" value = "${search_option}">
<input type = "hidden" id = "keyword" name = "keyword" value = "${keyword}">

<div style = "width : 800px;">
<textarea id = "r_content" name = "r_content" rows = "3" cols = "80"></textarea></div><br><br>
</form>	
	





<!-- 본인일 경우에만 댓글 수정버튼과 댓글 삭제 버튼이 출력되도록 설정함 -->

<div style = "width:700px; text-align:right;">

<c:if test = "${sessionScope.user_id == row.user_id or sessionScope.navername == row.user_id or sessionScope.kakaonickname == row.user_id or sessionScope.facebookname == row.user_id}">

<button type = "button" id = "btn_reply_Update" >댓글 수정</button>
<button type = "button" id = "btn_reply_Delete" >댓글 삭제</button>


</c:if>

<!-- 관리자아이디로 로그인했을 경우에 삭제버튼을 출력 -->
<c:if test = "${sessionScope.admin_id != null}">

<button type = "button" id = "btn_reply_Delete" >댓글 삭제</button>


</c:if>








</div>


<br><br>
</td>
</tr>



</c:forEach>
</table>
</c:if>


</body>
</html>