<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>빵집찾기 관리자 글쓰기</title>
<link href= "<%=request.getContextPath()%>/resources/css/reviewWriteStyle.css" type-"text/css" rel="stylesheet" >
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




<!-- 글쓰기 폼이 포함된 컨테이너 -->
<div class="form-container">
    <h2>게시물 작성</h2>
    
    <!-- 폼 시작, 서버에 데이터를 전송하기 위한 POST 방식 -->
    <form action="/submit" method="POST" enctype="multipart/form-data">
        
        <!-- 빵집 이름 입력 필드 -->
        <label for="bakery-name">빵집 이름</label>
        <input type="text" id="bakery-name" name="bakery_name">

        <!-- 리뷰 내용 입력 필드 -->
        <label for="review-content">리뷰 내용</label>
        <textarea id="review-content" name="review_content" rows="5"></textarea>

        <!-- 이미지 업로드 입력 필드, 여러 개의 이미지 선택 가능 -->
        <label for="images">이미지</label>
        <input type="file" id="images" name="images" multiple accept="image/*">

        <!-- 주소 입력 필드 -->
        <label for="address">주소</label>
        <input type="text" id="address" name="address" >

        <!-- 전화번호 입력 필드 -->
        <label for="phone">전화번호</label>
        <input type="text" id="phone" name="phone">

        <!-- 운영시간 입력 필드 -->
        <label for="business-hours">운영시간</label>
        <input type="text" id="business-hours" name="business_hours">

        <!-- 주차 정보 입력 필드 -->
        <label for="parking-info">주차 정보</label>
        <input type="text" id="parking-info" name="parking_info">

        <!-- 메뉴 정보 입력 필드 -->
        <label for="menu-info">메뉴 정보</label>
        <textarea id="menu-info" name="menu_info" rows="4"></textarea>

        <!-- 지도 (위도, 경도) 입력 필드 -->
        <label for="location">지도 (위도, 경도)</label>
        <input type="text" id="latitude" name="latitude" placeholder="위도">
        <input type="text" id="longitude" name="longitude" placeholder="경도">

        <!-- 지역 입력 필드 (카테고리 없이 지역만) -->
        <label for="area">지역</label>
        <input type="text" id="area" name="area">

        <!-- 게시물 등록 버튼 -->
        <button type="submit">게시물 등록</button>
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