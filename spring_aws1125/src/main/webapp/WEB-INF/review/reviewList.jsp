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
<link href= "<%=request.getContextPath()%>/resources/css/reviewListStyle.css" type="text/css" rel="stylesheet" >
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
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=서울'">서울</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=경기'">경기</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=경남/울산'">경남/울산</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=경북'">경북</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=강원'">강원</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=광주'">광주</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=대전'">대전</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=대구'">대구</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=부산'">부산</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=인천'">인천</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=전남'">전남</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=전북'">전북</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=충남'">충남</div>
	<div class="category-item" onclick="location.href='<%=request.getContextPath()%>/review/reviewList.aws?area=충북/세종'">충북/세종</div>
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
    

</body>
</html>