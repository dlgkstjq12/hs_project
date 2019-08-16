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
    if(confirm("수정하시겠습니까?")){
    document.form1.action="reply_update.do";
    document.form1.submit();
    		}
		});

	
	//댓글 삭제 버튼
	$("#btn_reply_Delete").click(function(){
	    if(confirm("삭제하시겠습니까?")){
	    	var rno = document.form2['rno'].value;
	        document.form2.action="reply_delete.do";
	        document.form2.submit();
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
<td><br><br>닉네임 : ${row.user_id}    작성일자 : ${row.reg_date}<br><br><br>
${row.r_content}<br><br>




<!-- 본인일 경우에만 댓글 수정버튼과 댓글 삭제 버튼이 출력되도록 설정함 -->

<div style = "width:700px; text-align:right;">
<c:if test = "${sessionScope.user_id == row.user_id }">
${row.rno }
<button type = "button" id = "btn_reply_Update">댓글 수정</button>
<button type = "button" id = "btn_reply_Delete">댓글 삭제</button>
</c:if>
</div>


<br><br>
</td>
</tr>
<form id="form1" name="form1" method="post">
<input type = "hidden" name = "rno" value = '${row.rno }'>
</form>



<form id="form2" name="form2" method="post">
<input type = "hidden" name = "rno" value = '${row.rno }'>
</form>



</c:forEach>
</table>
</c:if>

</body>
</html>