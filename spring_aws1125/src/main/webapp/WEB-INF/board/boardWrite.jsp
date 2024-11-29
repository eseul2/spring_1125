<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자유게시판 글쓰기</title>
<link href= "<%=request.getContextPath()%>/resources/css/boardWriteStyle.css" type-"text/css" rel="stylesheet" >
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


    <div class="container">
        <!-- 제목 입력 박스 -->
        <div class="form-group">
            <label for="title">&#128394;&#65039;제목:</label>
            <input type="text" id="title" name="title" class="input-box" required>
        </div>

        <!-- 글 내용 입력 박스 -->
        <div class="form-group">
            <label for="content">&#128221;내용:</label>
            <textarea id="content" name="content" class="input-box" rows="10" required></textarea>
        </div>

        <!-- 첨부파일 업로드 버튼 -->
        <div class="form-group">
            <label for="file">첨부파일:</label>
            <input type="file" id="filename" name="file">
        </div>

        <!-- 저장하기 버튼 -->
        <div class="form-group">
            <button type="submit" class="btn" onclick="savePost()">저장하기</button>
        </div>
    </div>








    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
<footer class="custom-footer">
	<div class="footer-content">
		<p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
	</div>
</footer>

</body>
</html>