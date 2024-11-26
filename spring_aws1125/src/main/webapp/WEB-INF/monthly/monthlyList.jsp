<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>이달의 빵집</title>
<link href= "<%=request.getContextPath()%>/resources/css/monthlyListStyle.css" type-"text/css" rel="stylesheet" >
</head>
<body>

<header class="header">
	<div class="logo">
		빵지순례
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


<main>
  <div class="search-section">
    <h1>이달의 빵집 &#129360;</h1>
    <div class="search-container">
      <input type="text" class="search-input" placeholder="검색어를 입력하세요">
      <button class="search-button">&#128269;</button>
    </div>
  </div>
</main>


<!-- 부드럽고 얇은 선 추가 -->
<hr style="border: 1px solid #ddd; margin: 20px 0;">






    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>  



</body>
</html>