<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/menu.jsp"%><br><br>
<script src = "${path}/ckeditor/ckeditor.js"></script>
<!-- ckeditor 사용을 위해 js파일을 연결함 -->
<body>
<!-- 글쓰기 폼 작성 -->
<h2>글쓰기</h2>
<form method = "post" action = "insert.do">

<div>제목 : <input name = "title" id = "title" size = "80" placeholder = "제목을 입력하세요"></div><br><br>

<div style = "width:800px; height:100px;"> <textarea id = "content" name = "content" rows = "6" cols = "80" placeholder = "내용을 입력하세요"></textarea></div>
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


<div style = "width:700px; text-align:right;" ><button type = "submit" name = "submit">확인</button></div>

<script>
//id가 description인 태그에 ckeditor을 적용시킴
//이미지 업로드 안됨
CKEDITOR.replace("content");




</script>

</form>
</body>
</html>