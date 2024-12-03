<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>이달의 빵집 수정하기</title>
<link href= "<%=request.getContextPath()%>/resources/css/monthlyWriteStyle.css" type-"text/css" rel="stylesheet" >

<!-- TinyMCE 스크립트 추가 -->
<script src="https://cdn.tiny.cloud/1/kim30qal93k1j6w6rqi9isinuueeqjn0bb3z9m4y040lj1lg/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>

<script>
tinymce.init({
    selector: "textarea", // TinyMCE를 적용할 textarea 선택
    plugins: "advlist autolink link image lists charmap preview paste", // 플러그인 설정
    toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist | link image", // 툴바 설정
    menubar: false, // 메뉴바 비활성화
    width: 900, 
    height: 800, // 에디터 높이 설정

    paste_data_images: true, // 클립보드에서 복사한 이미지를 Base64로 처리
    automatic_uploads: false, // 이미지 자동 업로드 비활성화

    // 이미지 업로드를 Base64로 처리
    images_upload_handler: function (blobInfo, success, failure) {
        const reader = new FileReader(); // FileReader를 사용하여 파일을 Base64로 변환
        reader.onload = function () {
            success(reader.result); // Base64 데이터를 TinyMCE에 전달
        };
        reader.onerror = function () {
            failure('이미지를 처리하는 동안 문제가 발생했습니다.');
        };
        reader.readAsDataURL(blobInfo.blob()); // 파일을 Base64로 읽기
    },

    // 붙여넣기 이미지를 Base64로 처리
    paste_postprocess: function(plugin, args) {
        console.log("붙여넣기 처리 중:", args.node.innerHTML);
    }
});


function check() {

	var fm = document.frm;
	
	if(fm.msubject.value == "") {
		alert("제목을 입력해주세요");
		fm.msubject.focus();  // 커서가 입력안한 해당 자리로 갈수 있도록 
		return;
	}
	
	var ans = confirm("저장하시겠습니까?");
	
	
	if(ans == true) {	
		fm.action="<%=request.getContextPath()%>/monthly/monthlyModifyAction.aws"; 
		fm.method="post"; 
		fm.enctype="multipart/form-data";   //파일을 올리기 위해서 지정해야한다. 
		fm.submit();	//파일 업로드를 포함한 폼 데이터를 전송할 때 필요한 인코딩 방식을 지정
	}
		return; 
}
</script>

</head>
<body>

    <form name="frm" class="form-container">
    <input type="hidden" name="mbidx" value="${monv.mbidx}">  <!-- mbidx값이 수정할때 필요해서 hidden으로 안보이게 한 input에 넣어서 controller로 보낸다. -->
        <div class="form-group">
            <label for="subject">제목:</label>
            <input type="text" id="msubject" name="msubject" class="input-title" value="${monv.msubject}">
        </div>
        
		<div class="form-group">
            <label for="introduction">소개글:</label>
            <input type="text" id="introduction" name="introduction" class="input-introduction" value="${monv.introduction}">
        </div>
        
		<div class="form-group">
            <label for="file">썸네일:</label>
            <input type="file" id="attachfile" name="attachfile" class="input-file">
        </div>
        
        <textarea name="mcontents" class="textarea-content" >${monv.mcontents}"</textarea>
        <button type="button" class="submit-button" onclick="check();">제출</button>
    </form>

</body>
</html>