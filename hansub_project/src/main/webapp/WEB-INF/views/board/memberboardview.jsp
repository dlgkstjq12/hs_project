<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/menu.jsp" %>
<script src="${path}/include/js/common.js"></script>
<script src="${path}/ckeditor/ckeditor.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(function(){//목록 버튼
$("#btnList").click(function(){
    location.href="list.do";
	});
});

//수정 버튼
$(function(){$("#btnUpdate").click(function(){
    //첨부파일 이름들을 폼에 추가
    if(confirm("수정하시겠습니까?")){
    document.form1.action="update.do";
    document.form1.submit();
    	}
	});
});


//삭제 버튼
$(function(){$("#btnDelete").click(function(){
    if(confirm("삭제하시겠습니까?")){
        document.form1.action="delete.do";
        document.form1.submit();
    	}
	});
});

listAttach();

</script>

<h2>게시물 보기</h2>
<!-- 게시물을 작성하기 위해 컨트롤러의 insert.do로 맵핑 -->
<form id="form1" name="form1" method="post"
action="${path}/board/insert.do">
    <div>제목 <input name="title" id="title" size="80"
                    value="${dto.title}"
                    placeholder="제목을 입력하세요"><br><br>
<!-- placeholder은 제목을 입력할 수 있도록 도움말을 출력함 -->
    </div>
    <div>조회수 : ${dto.viewcnt}    </div><br><br>
    <div style="width:800px;">
        내용 <textarea id="content" name="content"
rows="3" cols="80" 
placeholder="내용을 입력하세요">${dto.content}</textarea><br><br>
 
<!-- 마찬가지로 내용을 입력하도록 도움말을 출력함 -->
<script>
// ckeditor 적용
//id가 content인 태그 (글의 내용을 입력하는 태그)를 ck에디터를 적용한다는 의미
CKEDITOR.replace("content",{
    filebrowserUploadUrl: "${path}/imageUpload.do",
    height: "300px"
});
</script>

<div style = "width:700px; text-align:center;">
<!-- 수정, 삭제에 필요한 글번호를 hidden 태그에 저장한다. -->
	<input type = "hidden" name = "member_bno" value = "${dto.member_bno }">
	
	<!-- 본인만 수정, 삭제 버튼을 표시한다. -->
	<c:if test = "${sessionScope.user_id == dto.user_id }">
			<button type = "button" id = "btnUpdate">수정</button>
			<button type = "button" id = "btnDelete">삭제</button>
	</c:if>
	
	<!-- 글목록은 본인이 아니어도 확인 가능하게 한다. -->
	<button type = "button" id = "btnList">목록</button>

<body>

</body>
</html>