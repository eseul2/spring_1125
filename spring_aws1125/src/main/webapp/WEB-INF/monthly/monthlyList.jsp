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
			<li><a href ="<%=request.getContextPath()%>/bookmark/bookmarkList.aws">🤍</a></li>
		</ul>
	</nav>
<div class="separator"></div>
</header>


<main>
  <div class="search-section">
    <h1>이달의 빵집 &#129360;</h1>
    <p class="monthly-description">한 달에 한 번, 특별한 빵집을 소개합니다!</p>
    <div class="search-container">
      <input type="text" class="search-input" placeholder="이달의 매거진을 검색할 수 있습니다">
      <button class="search-button">&#128269;</button>
    </div>
  </div>
</main>


<!-- 부드럽고 얇은 선 추가 -->
<hr style="border: 1px solid #ddd; margin: 20px 0;">



<div class="card-container">
  <!-- 하드코딩된 게시물 카드 1 -->
  <div class="card">
    <img src="<%= request.getContextPath() %>/resources/images/main.jpg" alt="빵집 이미지 1" class="card-image">
    <div class="card-content">
      <h3 class="card-title">빵집 A</h3>
      <p class="card-text">이 빵집은 신선한 재료로 매일 갓 구운 빵을 제공합니다. 꼭 방문해보세요!</p>
      <p class="card-date">2024-11-26</p> <!-- 날짜 추가 -->
    </div>
  </div>
  
  <!-- 하드코딩된 게시물 카드 2 -->
  <div class="card">
    <img src="<%= request.getContextPath() %>/resources/images/main.jpg" alt="빵집 이미지 2" class="card-image">
    <div class="card-content">
      <h3 class="card-title">빵집 B</h3>
      <p class="card-text">크로와상이 맛있는 이 빵집은 동네 사람들에게 사랑받는 장소입니다.</p>
      <p class="card-date">2024-11-26</p> <!-- 날짜 추가 -->
    </div>
  </div>

  <!-- 하드코딩된 게시물 카드 3 -->
  <div class="card">
    <img src="<%= request.getContextPath() %>/resources/images/test.png" alt="빵집 이미지 3" class="card-image">
    <div class="card-content">
      <h3 class="card-title">빵집 C</h3>
      <p class="card-text">풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!</p>
      <p class="card-date">2024-11-26</p> <!-- 날짜 추가 -->
    </div>
  </div>
  <!-- 하드코딩된 게시물 카드 1 -->
  <div class="card">
    <img src="<%= request.getContextPath() %>/resources/images/main.jpg" alt="빵집 이미지 1" class="card-image">
    <div class="card-content">
      <h3 class="card-title">빵집 A</h3>
      <p class="card-text">이 빵집은 신선한 재료로 매일 갓 구운 빵을 제공합니다. 꼭 방문해보세요!</p>
      <p class="card-date">2024-11-26</p> <!-- 날짜 추가 -->
    </div>
  </div>
  
  <!-- 하드코딩된 게시물 카드 2 -->
  <div class="card">
    <img src="<%= request.getContextPath() %>/resources/images/main.jpg" alt="빵집 이미지 2" class="card-image">
    <div class="card-content">
      <h3 class="card-title">빵집 B</h3>
      <p class="card-text">크로와상이 맛있는 이 빵집은 동네 사람들에게 사랑받는 장소입니다.</p>
      <p class="card-date">2024-11-26</p> <!-- 날짜 추가 -->
    </div>
  </div>

  <!-- 하드코딩된 게시물 카드 3 -->
  <div class="card">
    <img src="<%= request.getContextPath() %>/resources/images/test.png" alt="빵집 이미지 3" class="card-image">
    <div class="card-content">
      <h3 class="card-title">빵집 C</h3>
      <p class="card-text">풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!
      풍부한 초코 브라우니가 자랑인 빵집입니다. 달콤한 행복을 느껴보세요!</p>
      <p class="card-date">2024-11-26</p> <!-- 날짜 추가 -->
    </div>
  </div>
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
	
	
	<!-- 글쓰기 버튼 (관리자만 보이게) -->
<%-- <% if (grade.equals("admin")) { %> --%>
  <div class="write-button-container">
    <a href="<%= request.getContextPath()%>/monthly/monthlyWrite.aws" class="write-button">글쓰기</a>
  </div>
<%-- <% } %> --%>



    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>  



</body>
</html>