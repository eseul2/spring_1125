<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@ page import="com.myaws1125.myapp.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%
 ArrayList<MonthlyVo> mlist = (ArrayList<MonthlyVo>)request.getAttribute("mlist");
 PageMaker pm = (PageMaker)request.getAttribute("pm"); 
 
 // 게시물 목록 순서 나타내기 
 int totalCount = pm.getTotalCount();  //전체갯수를 뽑아왔어 
 
 String keyword = pm.getScri().getKeyword();
 String searchType = pm.getScri().getSearchType();
 
 String param = "keyword="+keyword+"&searchType="+searchType+""; 
 %> 
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
			<!-- 회원번호가 있으면 담아놓은 회원이름을 출력하고 로그아웃 버튼을 만들어놓는다. -->
			<li><!-- 값이 비어있지 않으면 -->
				<c:if test="${!empty midx}">
					${memberName}&nbsp;
				<a href='${pageContext.request.contextPath}/member/memberLogout.aws'>님,로그아웃</a> 
				</c:if>
			</li>
			<li><a href ="<%=request.getContextPath()%>/bookmark/bookmarkList.aws">🤍</a></li>
		</ul>
	</nav>
<div class="separator"></div>
</header>


<main>
  <div class="search-section">
    <h1>이달의 빵집 &#129360;</h1>
    <p class="monthly-description">한 달에 한 번, 특별한 빵집을 소개합니다!</p>
    <form name="frm" action="<%=request.getContextPath()%>/monthly/monthlyList.aws" method="get">
    <div class="search-container">
		<select name = "searchType" class="custom-select"> 
        	<option value="subject">제목</option>
        	<option value="contents">내용</option>
        </select>
      <input type="text" class="search-input" placeholder="이달의 매거진을 검색할 수 있습니다" name="keyword">
      <button type="submit" class="search-button">&#128269;</button>
    </div>
 	</form>
  </div>
  
</main>


<!-- 부드럽고 얇은 선 추가 -->
<hr style="border: 1px solid #ddd; margin: 20px 0;">


<div class="card-container">
	<% 
	int num = totalCount - (pm.getScri().getPage() - 1) * pm.getScri().getPerPageNum(); // 현재 페이지의 첫 번째 게시글 번호 계산
	for (MonthlyVo monv : mlist) { // 게시물 리스트 순회	
	%>
	<a href="<%=request.getContextPath() %>/monthly/monthlyContents.aws?mbidx=<%=monv.getMbidx() %>" class="card-link">
	<div class="card">
    <img src="<%= request.getContextPath() %>/monthly/displayFile.aws?fileName=<%=monv.getFilename()%>" alt="썸네일" class="card-image">
    <div class="card-content">
      <h3 class="card-title"><%=monv.getMsubject() %></h3>
      <p class="card-text"><%=monv.getIntroduction() %></p>
      <p class="card-date"><%=monv.getWriteday() %></p> <!-- 날짜 추가 -->
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
		        <a href="<%=request.getContextPath()%>/monthly/monthlyList.aws?page=<%=pm.getStartPage()-1%>&<%=param%>" class="prev">◀</a>
		    <% } else { %>
		        <!-- 이전 페이지가 없는 경우, "◀" 버튼을 비활성화 -->
		        <span class="prev disabled">◀</span>
		    <% } %>
	
		    <% 
		    // 시작 페이지부터 끝 페이지까지 반복하여 각 페이지 번호를 생성
		    for (int i = pm.getStartPage(); i <= pm.getEndPage(); i++) { %>
		        <a href="<%=request.getContextPath()%>/monthly/monthlyList.aws?page=<%=i%>&<%=param%>" 
		           class="<%= (i == pm.getScri().getPage()) ? "active" : "" %>"><%=i%></a>
		    <% } %>
		
		    <% 
		    // 다음 페이지가 있는 경우, "▶" 버튼을 활성화하여 다음 페이지로 이동할 수 있게 함
		    if (pm.isNext()) { %>
		        <a href="<%=request.getContextPath()%>/monthly/monthlyList.aws?page=<%=pm.getEndPage()+1%>&<%=param%>" class="next">▶</a>
		    <% } else { %>
		        <!-- 다음 페이지가 없는 경우, "▶" 버튼을 비활성화 -->
		        <span class="next disabled">▶</span>
		    <% } %>
		</div>
	
		
<!-- 글쓰기 버튼 (관리자만 보이게) -->
<c:if test="${sessionScope.grade == 'admin'}">
    <div class="write-button-container">
        <a href="${pageContext.request.contextPath}/monthly/monthlyWrite.aws" class="write-button">글쓰기</a>
    </div>
</c:if>



    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>  



</body>
</html>