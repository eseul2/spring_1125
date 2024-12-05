<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.myaws1125.myapp.domain.ReviewVo" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내용</title>
<link href= "<%=request.getContextPath()%>/resources/css/reviewContentsStyle.css" type="text/css" rel="stylesheet" >
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVPsD3FK0Uki9fB5bQv3c_nA_A_0Al2Uw&callback=initMap" async defer></script>
<script>

// 삭제 바로 해버리기
function Delete(review_id) {

	if (confirm("정말 삭제하시겠습니까?")) {
		// 삭제 요청 URL로 이동
		location.href = '<%=request.getContextPath()%>/review/reviewDelete.aws?review_id=' + review_id;
		}
	}


function initMap() {
    // 위도와 경도를 객체로 정의
    const position = { lat: parseFloat("${rv.latitude}"), lng: parseFloat("${rv.longitude}") };

    // 지도 생성
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 15,
        center: position,
    });

    // 마커 생성
    const marker = new google.maps.Marker({
        position: position,
        map: map,
        title: "Location",
    });
}


document.addEventListener('DOMContentLoaded', () => {
    let currentIndex = 0; // 현재 이미지 인덱스

    const images = document.querySelectorAll('.image-slider img'); // 이미지들
    const prevButton = document.querySelector('.prev');
    const nextButton = document.querySelector('.next');

    if (images.length > 0) {
        images[currentIndex].classList.add('active'); // 초기 활성화 이미지 설정

        prevButton.addEventListener('click', () => {
            images[currentIndex].classList.remove('active'); // 현재 이미지 비활성화
            currentIndex = (currentIndex === 0) ? images.length - 1 : currentIndex - 1; // 첫 번째 이미지로 돌아감
            images[currentIndex].classList.add('active'); // 새로운 이미지 활성화
        });

        nextButton.addEventListener('click', () => {
            images[currentIndex].classList.remove('active'); // 현재 이미지 비활성화
            currentIndex = (currentIndex === images.length - 1) ? 0 : currentIndex + 1; // 마지막 이미지에서 첫 번째로 돌아감
            images[currentIndex].classList.add('active'); // 새로운 이미지 활성화
        });
    }
});

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



<div class="bakery-container">
    <!-- 이미지 슬라이더 -->
    <div class="image-slider">
        <c:forEach var="fileName" items="${fileNames}">
            <img src="<%= request.getContextPath() %>/review/displayFile.aws?fileName=${fileName}" alt="이미지">
        </c:forEach>
        
        <!-- 슬라이더 내비게이션 -->
        <button class="prev">❮</button>
        <button class="next">❯</button>
    </div>

    <!-- 빵집 이름 -->
    <div class="bakery-info">
        <h1>${rv.bakery_name}</h1>
        <hr>
    </div>

    <!-- 리뷰 내용 -->
    <div class="review-box">
        <p>${rv.review_contents}</p>
    </div>

<div class="details-container">
    <div class="menu-box">
        <h3>${rv.menu_info}</h3>
    </div>
    <div class="details-box">
        <p><strong>주소:</strong> ${rv.address}</p>
        <p><strong>전화번호:</strong> ${rv.bakery_phone}</p>
        <p><strong>이용시간:</strong> ${rv.operating_hours}</p>
        <p><strong>주차/교통정보:</strong> ${rv.parking_info}</p>
    </div>
</div>
    
<%--     <!-- 지도 -->
	<div class="map-box">
    <!-- 위도와 경도를 활용해 Google Maps iframe 생성 -->
    <iframe 
        src="https://www.google.com/maps/embed/v1/view?key=AIzaSyBVPsD3FK0Uki9fB5bQv3c_nA_A_0Al2Uw&center=${rv.latitude},${rv.longitude}&zoom=17" 
        allowfullscreen 
        loading="lazy">
    </iframe>
	</div> --%>
	
	<!-- 지도 -->
	<div class="map-box">
    	<div id="map" style="height: 400px; width: 100%;"></div>
	</div>
	
	
	<!-- 수정, 삭제 링크 (관리자만 보이게) -->
	<c:if test="${sessionScope.grade == 'admin'}">
    	<div class="edit-delete">
			<a href="${pageContext.request.contextPath}/review/reviewModify.aws?review_id=${rv.review_id}">수정</a>
        	<a href="#" onclick="Delete(${rv.review_id}); return false;">삭제</a>
    	</div>
	</c:if>
	
</div>

    
    
    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>        



</body>
</html> 