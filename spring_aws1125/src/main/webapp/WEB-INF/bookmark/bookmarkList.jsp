<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@ page import="com.myaws1125.myapp.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%
 
 // 모델에서 전달된 데이터 받기
 ArrayList<ReviewVo> bookmarkedReviews = (ArrayList<ReviewVo>) request.getAttribute("bookmarkedReviews");

 %> 

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>찜 목록</title>
<link href= "<%=request.getContextPath()%>/resources/css/bookmarkListStyle.css" type-"text/css" rel="stylesheet" >
</head>
<!-- 제이쿼리 cdn 주소 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script> 

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


<!-- 찜 목록 제목 -->
<div class="bookmark-header">
    <h2>찜 목록</h2>
</div>

<!-- 구분선 -->
<hr class="section-divider">

<!-- 빵집 추천 미리보기 게시물 띄우기 -->
<section class="recommend-section">
    <div class="card-container">
        <% 
        for (ReviewVo rv : bookmarkedReviews) { // 게시물 리스트 순회 
            // 여러 개의 이미지 파일 이름을 쉼표로 구분하여 배열로 나누기
            String[] filenames = rv.getFilename().split(",");
            // 첫 번째 이미지 파일만 가져오기
            String firstImage = filenames.length > 0 ? filenames[0] : ""; // 첫 번째 이미지만 가져옴
        %>
        
        <div class="card">
        <a href="<%=request.getContextPath() %>/review/reviewContents.aws?review_id=<%=rv.getReview_id() %>" class="link">
            <!-- 첫 번째 이미지만 표시 -->
            <img src="<%= request.getContextPath() %>/review/displayFile.aws?fileName=<%= firstImage %>" alt="추천 빵집">
		</a>
            <div class="card-content">
                <div class="title-container">
                    <h3><strong><%=rv.getBakery_name()%></strong></h3>
                    <!-- 북마크 버튼 -->
       				<button class="remove-bookmark-btn" data-review-id="${rv.review_id}" 
                		onclick="removeBookmark(this)">
            			<i class="fa fa-heart"></i> 찜 해제
       				</button>
                </div>    
                <hr class="freview"> <!-- 미리보기 구분선 -->
                <div class="contact-info">
                    <p><span class="label">주소:</span> 
                    <span class="value"><%=rv.getAddress()%></span></p>
                </div>
                <hr class="freview1"> <!-- 미리보기 구분선 -->           
                <p><%=rv.getReview_contents()%></p>
            </div>
        </div>
        <%
        }
        %>    
    </div>
			<!-- 더보기 -->
		<div id="loadMoreCircle" class="load-more-circle" onclick="loadMore()">
    		+
		</div>
</section>


    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
<footer class="custom-footer">
	<div class="footer-content">
		<p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
	</div>
</footer>

<script>
function removeBookmark(btn) {
    var review_id = $(btn).data('review-id');
    $.ajax({
        url: "<%=request.getContextPath()%>/bookmark/toggleBookmark",
        type: "POST",
        data: { reviewId: reviewId },
        success: function(response) {
            if (response === "removed") {
                $(btn).closest('.bookmarked-review-item').remove();
            }
        }
    });
}
</script>   

</body>
</html>