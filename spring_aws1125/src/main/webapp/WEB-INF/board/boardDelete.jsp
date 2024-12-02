<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 메세지 출력 -->
<c:set var="msg" value="${requestScope.msg}" />
<c:if test="${!empty msg}">
    <script>alert('${msg}');</script>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 삭제</title>
<link href= "<%=request.getContextPath()%>/resources/css/boardDeleteStyle.css" type-"text/css" rel="stylesheet" >
<script> 

function check() {
	  
	  // 유효성 검사하기
	  let fm = document.frm;
	  
	  if (fm.password.value == "") {
		  alert("비밀번호를 입력해주세요");
		  fm.password.focus();
		  return;
	  }
	  
	  let ans = confirm("삭제하시겠습니까?");
	  
	  if (ans == true) {
		  fm.action="<%=request.getContextPath()%>/board/boardDeleteAction.aws";
		  fm.method="post";
		  fm.submit();
	  }	  
	  return;
}

</script>
</head>
<body>


<header class="header">
	<div class="logo">
		<a href ="<%=request.getContextPath()%>/member/main.aws">빵지순례</a>
	</div>
<div class="separator"></div>
        
        
<!-- 네비게이션 바 -->
	<nav class="navbar">
		<ul>
			<li><a href ="<%=request.getContextPath()%>/member/main.aws">홈</a></li>
			<li><a href ="<%=request.getContextPath()%>/review/reviewList.aws">빵집찾기</a></li>
			<li><a href ="<%=request.getContextPath()%>/monthly/monthlyList.aws">이달의 빵집</a></li>
			<li><a href ="<%=request.getContextPath()%>/board/boardList.aws">자유게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/member/memberLogin.aws">로그인</a></li>
			<li><a href ="<%=request.getContextPath()%>/bookmark/bookmarkList.aws">🤍</a></li>
		</ul>
	</nav>
<div class="separator"></div>
</header>


    <div class="delete-container">
        <h2>게시물 삭제</h2>
        <form name="frm">
		<input type="hidden" name="bidx" value="${bidx}">
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password">
            <button type="button" onclick="check();">삭제</button>
        </form>
    </div>



    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
<footer class="custom-footer">
	<div class="footer-content">
		<p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
	</div>
</footer>

</body>
</html>