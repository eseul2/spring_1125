<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@ page import="com.myaws1125.myapp.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%
 ArrayList<ReviewVo> rlist = (ArrayList<ReviewVo>)request.getAttribute("rlist");
 PageMaker pm = (PageMaker)request.getAttribute("pm"); 

 
 // 게시물 목록 순서 나타내기 
 int totalCount = pm.getTotalCount();  //전체갯수를 뽑아왔어 
 
 %> 

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인페이지</title>
<link href= "<%=request.getContextPath()%>/resources/css/mainStyle.css" type-"text/css" rel="stylesheet" >
</head>

<body>

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

<!-- 메인 콘텐츠 시작 -->
<section class="main-banner">
    <div class="banner-content">
        <!-- 배너 이미지 영역 -->
        <div class="banner-image">
            <img src="<%= request.getContextPath() %>/resources/images/titi.jpg" alt="빵집 이미지" />
        </div>
        
    
        <!-- 문구 영역 -->
        <div class="banner-text">
            <h1>이달의 빵집 &#127838;</h1>
            <p>이곳은 전국의 빵집을 소개하고 리뷰를 공유하는 공간입니다.<br>
               당신만의 숨은 맛집을 찾고 공유해보세요!<br>
               뭘 적어야 할지 모르겠네영~~~~~ <br>
               하지만 언젠가 생각이 나겠죠~~~~</p>
              <!-- 버튼을 banner-text 아래에 위치시키기 -->
        <button onclick="location.href='<%= request.getContextPath()%>/monthly/monthlyList.aws'" class="btn">이달의 빵집 가기</button> 
        </div>
    </div>
</section>


<!-- 부드럽고 얇은 선 추가 -->
<hr style="border: 1px solid #ddd; margin: 20px 0;">



<!-- 빵집 추천 미리보기 게시물 띄우기 -->
<section class="recommend-section">
    <h2>Bakery Find &#128269;</h2>
    <div class="card-container">
<%
    // rlist에서 상위 8개만 가져오기
    int maxItems = Math.min(rlist.size(), 8);  // rlist 크기와 8 중 작은 값을 선택
    for (int i = 0; i < maxItems; i++) {
        ReviewVo rv = rlist.get(i);
        // 이미지 파일 처리 및 기타 내용 출력
        String[] filenames = rv.getFilename().split(",");
        String firstImage = filenames.length > 0 ? filenames[0] : ""; // 첫 번째 이미지만 가져옴
%>
        <a href="<%=request.getContextPath() %>/review/reviewContents.aws?review_id=<%=rv.getReview_id() %>" class="link">
        <div class="card">
            <!-- 첫 번째 이미지만 표시 -->
            <img src="<%= request.getContextPath() %>/review/displayFile.aws?fileName=<%= firstImage %>" alt="추천 빵집">
            <div class="card-content">
                <div class="title-container">
                    <h3><strong><%=rv.getBakery_name()%></strong></h3>
                    <!-- 북마크 버튼 -->
                    <button class="bookmark-btn" data-review-id="1">
                        <span class="bookmark-icon">&#9825;</span> <!-- 빈 하트 -->
                    </button>
                </div>
                
                <!-- 가로 구분선 -->
                <hr class="freview"> <!-- 미리보기 구분선 -->
                <div class="contact-info">
                    <p><span class="label">주소:</span> 
                    <span class="value"><%=rv.getAddress()%></span></p>
                </div>
                <hr class="freview1"> <!-- 미리보기 구분선 -->           
                <p><%=rv.getReview_contents()%></p>
            </div>
        </div>
        </a>
<%
    }
%>
 
    </div>
    
</section>
<!-- 메인 콘텐츠 끝 -->


    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>
    
    
</body>
</html>