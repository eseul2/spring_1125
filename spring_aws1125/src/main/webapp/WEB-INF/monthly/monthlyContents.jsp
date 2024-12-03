<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.myaws1125.myapp.domain.MonthlyVo" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글쓰기</title>
<link href= "<%=request.getContextPath()%>/resources/css/boardContentsStyle.css" type-"text/css" rel="stylesheet" >
</head>
<!-- 제이쿼리 cdn 주소 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script> 

<script>
    
function confirmDelete(mbidx) {

	if (confirm("정말 삭제하시겠습니까?")) {
		// 삭제 요청 URL로 이동
		location.href = '<%=request.getContextPath()%>/monthly/monthlyDelete.aws?mbidx=' + mbidx;
		}
	}
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

 	<!-- 글 상세보기 박스 -->
    <div class="post-box">
        <!-- 제목 -->
        <h1 class="post-title">${monv.msubject}</h1>
        
        <!-- 작성자, 작성일 -->
        <div class="post-meta">
         작성일: ${monv.writeday}
        </div>

        <!-- 구분선 -->
        <div class="separator"></div>
        
		<!-- 조회수 -->
    	<div class="view-count">
        	조회수: ${monv.viewcnt}
    	</div>

	<!-- 수정, 삭제 링크 (관리자만 보이게) -->
	<c:if test="${sessionScope.grade == 'admin'}">
    	<div class="edit-delete">
			<a href="${pageContext.request.contextPath}/monthly/monthlyModify.aws?mbidx=${monv.mbidx}">수정</a>
        	<a href="#" onclick="confirmDelete(${monv.mbidx}); return false;">삭제</a>
    	</div>
	</c:if>

        
        
        <!-- 글 내용 -->
        <div class="post-content">
           ${monv.mcontents}
        </div>


        <!-- 목록으로 돌아가기 링크 -->
        <div class="post-list-link">
           <a href="<%=request.getContextPath()%>/monthly/monthlyList.aws">목록으로</a>
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