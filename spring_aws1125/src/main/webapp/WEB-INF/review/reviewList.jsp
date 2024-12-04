<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@ page import="com.myaws1125.myapp.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%
 ArrayList<ReviewVo> rlist = (ArrayList<ReviewVo>)request.getAttribute("rlist");
 PageMaker pm = (PageMaker)request.getAttribute("pm"); 
 
 // 게시물 목록 순서 나타내기 
 int totalCount = pm.getTotalCount();  //전체갯수를 뽑아왔어 
 
 String keyword = pm.getScri().getKeyword();
 
 String param = "keyword="+keyword; 
 %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>빵집 찾기</title>
<link href= "<%=request.getContextPath()%>/resources/css/reviewListStyle.css" type-"text/css" rel="stylesheet" >
</head>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVPsD3FK0Uki9fB5bQv3c_nA_A_0Al2Uw&callback=myMap"></script>
<script>



//모달 요소 및 닫기 버튼 가져오기
const modal = document.getElementById('modal-popup'); // 모달 창 요소
const closeBtn = document.querySelector('.close-btn'); // 모달 닫기 버튼 요소 

// 모달 열기 함수 
function openModal() {
  modal.style.display = 'flex'; // 모달을 화면에 표시 
}

// 모달 닫기 함수 
function closeModal() {
  modal.style.display = 'none'; // 모달을 화면에서 숨김
}

// 닫기 버튼 클릭시 모달 닫기 이벤트 추가
closeBtn.addEventListener('click', closeModal);

// 이미지 슬라이더
let currentSlide = 0; // 현재 활성화된 슬라이드 번호 
const slides = document.querySelectorAll('.slides img'); // 모든 슬라이드 이미지 모음
const totalSlides = slides.length; // 슬라이드 총 개수 

// 이전 버튼 클릭시 슬라이드 이동 
document.querySelector('.prev-btn').addEventListener('click', () => {
  slides[currentSlide].style.display = 'none'; // 현재 슬라이드를 숨김
  currentSlide = (currentSlide - 1 + totalSlides) % totalSlides; // 이전 슬라이드로 이동
  slides[currentSlide].style.display = 'block'; // 이전 슬라이드를 표시 
});

// 다음 버튼 클릭시 슬라이드 이동 
document.querySelector('.next-btn').addEventListener('click', () => {
  slides[currentSlide].style.display = 'none'; // 현재 슬라이드를 숨김
  currentSlide = (currentSlide + 1) % totalSlides; // 다음 슬라이드로 이동(순환)
  slides[currentSlide].style.display = 'block'; // 다음 슬라이드를 표시 
});

// 초기 슬라이드 설정
slides.forEach((slide, index) => {
  slide.style.display = index === 0 ? 'block' : 'none'; // 첫 슬라이드만 표시, 나머지는 숨김
});

</script>

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


<main>
  <div class="search-section">
    <h1>어떤 빵집을 찾으시나요?</h1>
<form name="frm" action="<%=request.getContextPath()%>/review/reviewList.aws" method="get"> 
    <div class="search-container">
      <input type="text" class="search-input" placeholder="검색어를 입력하세요" name="keyword">
      <button type="submit" class="search-button">&#128269;</button>
    </div>
</form>      
  </div>
</main>



<!-- 부드럽고 얇은 선 추가 -->
<hr style="border: 1px solid #ddd; margin: 20px 0;">



<!-- 카테고리 섹션 -->
<div class="category-section">
  <div class="category-item" onclick="showRestaurants('서울')">서울</div> <!-- 함수 이름 바꾸기 -->
  <div class="category-item" onclick="showRestaurants('경기')">경기</div>
  <div class="category-item" onclick="showRestaurants('경남/울산')">경남/울산</div>
  <div class="category-item" onclick="showRestaurants('경북')">경북</div>
  <div class="category-item" onclick="showRestaurants('강원')">강원</div>
  <div class="category-item" onclick="showRestaurants('광주')">광주</div>
  <div class="category-item" onclick="showRestaurants('대전')">대전</div>
  <div class="category-item" onclick="showRestaurants('대구')">대구</div>
  <div class="category-item" onclick="showRestaurants('부산')">부산</div>
  <div class="category-item" onclick="showRestaurants('인천')">인천</div>
  <div class="category-item" onclick="showRestaurants('전남')">전남</div>
  <div class="category-item" onclick="showRestaurants('전북')">전북</div>
  <div class="category-item" onclick="showRestaurants('충남')">충남</div>
  <div class="category-item" onclick="showRestaurants('충북/세종')">충북/세종</div>
</div>



<!-- 빵집 추천 미리보기 게시물 띄우기 -->
<section class="recommend-section">
    <div class="card-container">
        <% 
        int num = totalCount - (pm.getScri().getPage() - 1) * pm.getScri().getPerPageNum(); // 현재 페이지의 첫 번째 게시글 번호 계산
        for (ReviewVo rv : rlist) { // 게시물 리스트 순회 
            // 여러 개의 이미지 파일 이름을 쉼표로 구분하여 배열로 나누기
            String[] filenames = rv.getFilename().split(",");
            // 첫 번째 이미지 파일만 가져오기
            String firstImage = filenames.length > 0 ? filenames[0] : ""; // 첫 번째 이미지만 가져옴
        %>
        <div class="card" onclick="openModal('bakery')">
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
        <%
        num = num-1;
        }
        %>    
    </div>

    
        
	<!-- 페이지네이션 (페이지 이동) -->
	<div class="pagination">
		<% 
	// 이전 페이지가 있는 경우, "◀" 버튼을 활성화하여 이전 페이지로 이동할 수 있게 함
		if (pm.isPrev()) { %>
			<a href="<%=request.getContextPath()%>/review/reviewList.aws?page=<%=pm.getStartPage()-1%>&<%=param%>" class="prev">◀</a>
		<% } else { %>
			<!-- 이전 페이지가 없는 경우, "◀" 버튼을 비활성화 -->
			<span class="prev disabled">◀</span>
		<% } %>
	
		<% 
		// 시작 페이지부터 끝 페이지까지 반복하여 각 페이지 번호를 생성
		for (int i = pm.getStartPage(); i <= pm.getEndPage(); i++) { %>
			<a href="<%=request.getContextPath()%>/review/reviewList.aws?page=<%=i%>&<%=param%>" 
			class="<%= (i == pm.getScri().getPage()) ? "active" : "" %>"><%=i%></a>
		<% } %>
		
		<% 
		// 다음 페이지가 있는 경우, "▶" 버튼을 활성화하여 다음 페이지로 이동할 수 있게 함
		if (pm.isNext()) { %>
			<a href="<%=request.getContextPath()%>/review/reviewList.aws?page=<%=pm.getEndPage()+1%>&<%=param%>" class="next">▶</a>
		<% } else { %>
			<!-- 다음 페이지가 없는 경우, "▶" 버튼을 비활성화 -->
			<span class="next disabled">▶</span>
		<% } %>
	</div>
</section>


<!-- 글쓰기 버튼 (관리자만 보이게) -->
<c:if test="${sessionScope.grade == 'admin'}">
    <div class="write-button-container">
        <a href="${pageContext.request.contextPath}/review/reviewWrite.aws" class="write-button">글쓰기</a>
    </div>
</c:if>
<!-- 메인 콘텐츠 끝 -->






    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>    
    
    
<!-- 모달 팝업 -->
<div id="modal-popup" class="modal">
  <div class="modal-content">
    <!-- 닫기 버튼 -->
    <button class="close-btn" onclick="closeModal()">&times;</button>

    <!-- 이미지 슬라이드 -->
    <div class="image-slider">
      <div class="slides" id="modalSlides">
        <!-- 이미지 슬라이드는 JavaScript로 추가 -->
      </div>
      <button class="prev-btn" onclick="prevSlide()">&#10094;</button>
      <button class="next-btn" onclick="nextSlide()">&#10095;</button>
    </div>

    <!-- 가게 이름 -->
    <h2 id="bakery_name" class="store-name"></h2>

    <!-- 가로 구분선 -->
<hr class="modal-divider">

    <!-- 리뷰 -->
    <p id="review_contents" class="store-review"></p>

    <!-- 메뉴 정보 -->
    <div class="menu-info">
      <h3>메뉴 정보</h3>
      <p id="menu_info"></p>
    </div>

    <!-- 가게 세부 정보 -->
    <div class="store-details">
      <p id="address"><strong>주소:</strong></p>
      <p id="bakery_phone"><strong>전화번호:</strong></p>
      <p id="parking_info"><strong>주차:</strong></p>
    </div>

    <!-- 지도 -->
    <div id="map" class="map">
      <iframe
        id="modalMap"
        src=""
        width="100%"
        height="300"
        frameborder="0"
        style="border:0"
        allowfullscreen
      ></iframe>
    </div>
  </div>
</div>



<!-- 자바스크립트 코드 -->
<script>
//빵집 데이터를 저장한 객체 배열 
const bakeryData = {
  bakery: {
    title: "${rv.bakery_name}",
    review: "${rv.review_contents}",
    images: [
      "<%= request.getContextPath() %>/resources/images/bakery1-2.jpg",
      "<%= request.getContextPath() %>/resources/images/bakery1-2.jpg",
      "<%= request.getContextPath() %>/resources/images/bakery1-3.jpg"
      ],
    menu: "${rv.menu_info}",
    address: "${rv.address}",
    phone: "${rv.bakery_phone}",
    parking: "${rv.bakery_phone}",
    mapSrc: "https://maps.google.com/maps?q=37.5172,127.0473&z=15&output=embed"
  }
};

// 현재 슬라이드 인덱스
let currentSlideIndex = 0;

// 모달 열기 함수 (선택된 빵집의 데이터를 기반으로 모달 내용 설정 )
function openModal(bakeryId) {
  const modal = document.getElementById("modal-popup"); // 모달 요소 
  const bakeryInfo = bakeryData[bakeryId]; // bakeryId에 해당하는 빵집 정보 가져오기

  if (bakeryInfo) {
    // 기본 정보 설정 모달 내부 텍스트 업데이트
    document.getElementById("bakery_name").innerText = bakeryInfo.title;
    document.getElementById("review_contents").innerText = `관리자 리뷰: ${bakeryInfo.review}`;
    document.getElementById("menu_info").innerText = bakeryInfo.menu;
    document.getElementById("address").innerText = `주소: ${bakeryInfo.address}`;
    document.getElementById("bakery_phone").innerText = `전화번호: ${bakeryInfo.phone}`;
    document.getElementById("parking_info").innerText = `주차: ${bakeryInfo.parking}`;
    document.getElementById("modalMap").src = bakeryInfo.mapSrc;

    // 이미지 슬라이드 설정
    const slidesContainer = document.getElementById("modalSlides");
    slidesContainer.innerHTML = ""; // 기존 슬라이드 초기화
    bakeryInfo.images.forEach((image, index) => {
      const imgElement = document.createElement("img");
      imgElement.src = image;
      imgElement.alt = `Slide ${index + 1}`;
      imgElement.style.display = index === 0 ? "block" : "none"; // 첫 슬라이드만 보이도록 설정
      slidesContainer.appendChild(imgElement);
    });

    // 모달 표시
    modal.style.display = "flex"; //중앙정렬
    currentSlideIndex = 0; // 초기화
  } else {
    console.error("유효하지 않은 bakeryId입니다."); // 에러처리
  }
}

// 모달 닫기
function closeModal() {
  const modal = document.getElementById("modal-popup");
  modal.style.display = "none";
}

// 이전 슬라이드로 이동
function prevSlide() {
  const slides = document.querySelectorAll("#modalSlides img");
  slides[currentSlideIndex].style.display = "none"; // 현재 슬라이드를 숨김
  currentSlideIndex = (currentSlideIndex - 1 + slides.length) % slides.length; // 이전 슬라이드로 이동
  slides[currentSlideIndex].style.display = "block"; // 이전 슬라이드 표시 
}

// 다음 슬라이드로 이동
function nextSlide() {
  const slides = document.querySelectorAll("#modalSlides img");
  slides[currentSlideIndex].style.display = "none"; // 현재 슬라이드를 숨김
  currentSlideIndex = (currentSlideIndex + 1) % slides.length; // 다음 슬라이드로 이동
  slides[currentSlideIndex].style.display = "block";  // 다음 슬라이드 표시 
}

// 모달 외부를 클릭했을 때 닫기
window.onclick = function (event) {
  const modal = document.getElementById("modal-popup");
  if (event.target === modal) {
    closeModal(); // 외부 클릭 시 모달 닫기
  }
};

</script>      

</body>
</html>