<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@ page import="com.myaws1125.myapp.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%
 ArrayList<BoardVo> blist = (ArrayList<BoardVo>)request.getAttribute("blist");
 //System.out.println("blist==>" + blist);
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자유게시판</title>
<link href= "<%=request.getContextPath()%>/resources/css/boardListStyle.css" type-"text/css" rel="stylesheet" >
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


	<!-- 게시판 제목과 설명 -->
	<div class="board-header">
    	<h1 class="board-title">자유게시판</h1>
    	<p class="board-description">자유롭게 글을 써보세요!</p>
	</div>

    <!-- 게시판 목록 영역 -->
    <div class="board-container">
        <!-- 검색바 -->
        <form name="frm" action="<%=request.getContextPath()%>/board/boardList.aws" method="get">
        <div class="search-bar">
        	<select name = "searchType" class="custom-select"> 
        		<option value="subject">제목</option>
        		<option value="writer">작성자</option>
        		<option value="contents">내용</option>
        	</select>
            <input type="text" placeholder="검색어를 입력하세요" name="keyword"> <!-- 키워드 검색 -->
            <button type="submit" class="search-btn">검색</button>
        </div>
        </form>

        <!-- 게시물 목록 -->
        <div class="board-list">
        	<% 
    		int num = totalCount - (pm.getScri().getPage() - 1) * pm.getScri().getPerPageNum(); // 현재 페이지의 첫 번째 게시글 번호 계산
    		for (BoardVo bv : blist) { // 게시물 리스트 순회	
			%>
            <!-- 개별 게시물 항목 -->
            <div class="board-item">		
				<a href="<%=request.getContextPath() %>/board/boardContents.aws?bidx=<%=bv.getBidx() %>"><%=bv.getSubject()%></a>
				<span class="date"><%=bv.getWriter() %> | <%=bv.getWriteday() %></span>
            </div>
            <%
		 	num = num-1;
			}
			%>

	       	 <!-- 글쓰기 버튼과 페이지네이션을 포함할 컨테이너 -->
       		 <div class="bottom-container">
            <!-- 글쓰기 버튼 -->
            <div class="write-btn-container">
                <a class="write-btn" href="<%=request.getContextPath()%>/board/boardWrite.aws">글쓰기</a>
            </div>

		<!-- 페이지네이션 (페이지 이동) -->
		<div class="pagination">
		    <% 
		    // 이전 페이지가 있는 경우, "◀" 버튼을 활성화하여 이전 페이지로 이동할 수 있게 함
		    if (pm.isPrev()) { %>
		        <a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getStartPage()-1%>&<%=param%>" class="prev">◀</a>
		    <% } else { %>
		        <!-- 이전 페이지가 없는 경우, "◀" 버튼을 비활성화 -->
		        <span class="prev disabled">◀</span>
		    <% } %>
	
		    <% 
		    // 시작 페이지부터 끝 페이지까지 반복하여 각 페이지 번호를 생성
		    for (int i = pm.getStartPage(); i <= pm.getEndPage(); i++) { %>
		        <a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=i%>&<%=param%>" 
		           class="<%= (i == pm.getScri().getPage()) ? "active" : "" %>"><%=i%></a>
		    <% } %>
		
		    <% 
		    // 다음 페이지가 있는 경우, "▶" 버튼을 활성화하여 다음 페이지로 이동할 수 있게 함
		    if (pm.isNext()) { %>
		        <a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getEndPage()+1%>&<%=param%>" class="next">▶</a>
		    <% } else { %>
		        <!-- 다음 페이지가 없는 경우, "▶" 버튼을 비활성화 -->
		        <span class="next disabled">▶</span>
		    <% } %>
		</div>
        </div>
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