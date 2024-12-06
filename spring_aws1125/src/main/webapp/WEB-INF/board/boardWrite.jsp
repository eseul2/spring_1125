<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시물 입력 작성 실패시 띄우는 메세지-->
<%
String msg = "";
if(request.getAttribute("msg") != null) {
msg = (String)request.getAttribute("msg");
}

if(msg != "") {  
out.println("<script>alert('"+msg+"');</script>");
}

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자유게시판 글쓰기</title>
<link href= "<%=request.getContextPath()%>/resources/css/boardWriteStyle.css" type-"text/css" rel="stylesheet" >
</head>
<body>


<script>
function check() {
	
	//유효성 검사하기
	var fm = document.frm;
	
	if(fm.subject.value == "") {
		alert("제목을 입력해주세요");
		fm.subject.focus();  // 커서가 입력안한 해당 자리로 갈수 있도록 
		return;
	} else if(fm.contents.value =="") {
		alert("내용을 입력해주세요");
		fm.contents.focus(); 
		return;
	}else if(fm.writer.value =="") {
		alert("작성자를 입력해주세요");
		fm.writer.focus(); 
		return;
	}else if(fm.password.value =="") {
		alert("비밀번호를 입력해주세요");
		fm.password.focus(); 
		return;
	}
	
	var ans = confirm("저장하시겠습니까?");  // 함수의 값은 참과 거짓 true false로 나눈다. 
	
	if(ans == true) {	
		fm.action="<%=request.getContextPath()%>/board/boardWriteAction.aws"; 
		fm.method="post";
		fm.enctype="multipart/form-data";   //파일을 올리기 위해서 지정해야한다. 
		fm.submit();	//파일 업로드를 포함한 폼 데이터를 전송할 때 필요한 인코딩 방식을 지정
	}
		return; 
}
</script>


<header class="header">
	<div class="logo">
		<a href ="<%=request.getContextPath()%>/review/main.aws">빵지순례</a>
	</div>
<div class="separator"></div>
        
        
<!-- 네비게이션 바 -->
	<nav class="navbar">
		<ul>
			<li><a href ="<%=request.getContextPath()%>/review/main.aws">홈</a></li>
			<li><a href ="<%=request.getContextPath()%>/review/reviewList.aws">빵집찾기</a></li>
			<li><a href ="<%=request.getContextPath()%>/monthly/monthlyList.aws">이달의 빵집</a></li>
			<li><a href ="<%=request.getContextPath()%>/board/boardList.aws">자유게시판</a></li>
			<!-- 회원번호가 있으면 담아놓은 회원이름을 출력하고 로그아웃 버튼을 만들어놓는다. -->
			<li><!-- 값이 비어있지 않으면 -->
				<c:if test="${!empty midx}">
					${memberName}님,
				<a href='${pageContext.request.contextPath}/member/memberLogout.aws'>로그아웃</a> 
				</c:if>
				<c:if test="${empty midx}">
				<a href="<%=request.getContextPath()%>/member/memberLogin.aws">로그인</a>
				</c:if>
			</li>
			<li><a href ="<%=request.getContextPath()%>/bookmark/bookmarkList.aws">🤍</a></li>
		</ul>
	</nav>
<div class="separator"></div>
</header>

<form name="frm">
    <div class="container">
        <!-- 제목 입력 박스 -->
        <div class="form-group">
            <label for="title">&#128394;&#65039;제목:</label>
            <input type="text" id="subject" name="subject" class="input-box" placeholder="제목을 입력하세요">
        </div>

        <!-- 글 내용 입력 박스 -->
        <div class="form-group">
            <label for="content">&#128221;내용:</label>
            <textarea id="contents" name="contents" class="input-box" rows="10" placeholder="내용을 입력하세요"></textarea>
        </div>

		
        <!-- 첨부파일, 작성자, 비밀번호 -->
        <div class="form-group file-writer-password">
            <div>
                <label for="file">📁첨부파일:</label>
                <input type="file" id="attachfile" name="attachfile">
            </div>
            <div>
                <label for="writer">작성자</label>
                <input type="text" id="writer" name="writer">
            </div>
            <div>
                <label for="pass">비밀번호</label>
                <input type="password" id="password" name="password">
            </div>
        </div>
        
        <!-- 저장하기 버튼 -->
        <div class="form-group2">
            <button type="button" class="btn" onclick="check();">저장</button>
            <button type="button" class="btn" onclick="history.back();">취소</button>
		</div>
        </div>
    </div>
</form>







    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
<footer class="custom-footer">
	<div class="footer-content">
		<p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
	</div>
</footer>

</body>
</html>