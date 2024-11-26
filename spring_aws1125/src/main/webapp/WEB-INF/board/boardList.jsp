<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자유게시판</title>
<link href= "<%=request.getContextPath()%>/resources/css/boardListStyle.css" type-"text/css" rel="stylesheet" >
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
		</ul>
	</nav>
<div class="separator"></div>
</header>


	<!-- 게시판 제목과 설명 -->
	<div class="board-header">
    	<h1 class="board-title">자유게시판</h1>
    	<p class="board-description">자유롭게 글을 써보세요!</p>
	</div>


    <!-- 게시판 목록 영역 -->
    <div class="board-container">
        <!-- 검색바 -->
        <div class="search-bar">
            <input type="text" placeholder="검색어를 입력하세요">
            <button type="button" class="search-btn">검색</button>
        </div>

        <!-- 게시물 목록 -->
        <div class="board-list">
            <!-- 개별 게시물 항목 -->
            <div class="board-item">
                <a href="#">[공지] 2025년 사이트 리뉴얼 예정</a>
                <span class="date">홍길동 | 2024.11.14 18:10</span>
            </div>
            <div class="board-item">
                <a href="#">[공지] 회원 가입 안내</a>
                <span class="date">홍길동 | 2024.11.14 18:10</span>
            </div>
            <!-- 추가 게시물 목록 추가 -->
			<div class="board-item">
                <a href="#">[공지] 회원 가입 안내</a>
                <span class="date">홍길동 | 2024.11.14 18:10</span>
            </div>
            <div class="board-item">
                <a href="#">[공지] 회원 가입 안내</a>
                <span class="date">홍길동 | 2024.11.14 18:10</span>
            </div>
   			<div class="board-item">
                <a href="#">[공지] 회원 가입 안내</a>
                <span class="date">홍길동 | 2024.11.14 18:10</span>
            </div>
   			<div class="board-item">
                <a href="#">[공지] 회원 가입 안내</a>
                <span class="date">홍길동 | 2024.11.14 18:10</span>
            </div>
   
       		<!-- 글쓰기 버튼 -->
		<div class="write-btn-container">
			<a class="write-btn" href="<%=request.getContextPath()%>/board/boardWrite.aws">글쓰기</a>
		</div>
		
        <!-- 페이지네이션 (페이지 이동) -->
        <div class="pagination">
            <a href="#" class="prev">◀</a>
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#" class="next">▶</a>
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