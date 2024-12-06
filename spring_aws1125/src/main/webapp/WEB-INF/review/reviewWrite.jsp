<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 게시물 입력 작성 실패시 띄우는 메세지-->
<%
String msg = "";
if(request.getAttribute("msg") != null) {
msg = (String)request.getAttribute("msg");
}

if(msg != "") {  
out.println("<script>alert('"+msg+"');</script>");
}

%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>빵집찾기 관리자 글쓰기</title>
<link href= "<%=request.getContextPath()%>/resources/css/reviewWriteStyle.css" type="text/css" rel="stylesheet" >

<script>

function reviewCheck() {

	var fm = document.frm;
	
	
	var ans = confirm("저장하시겠습니까?");
	
	if(ans == true) {	
		fm.action="<%=request.getContextPath()%>/review/reviewWriteAction.aws"; 
		fm.method="post"; 
		fm.enctype="multipart/form-data";   //파일을 올리기 위해서 지정해야한다. 
		fm.submit();	//파일 업로드를 포함한 폼 데이터를 전송할 때 필요한 인코딩 방식을 지정
	}
		return; 
}

</script>


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




<!-- 글쓰기 폼이 포함된 컨테이너 -->
<div class="form-container">
    <h2>게시물 작성</h2>
    
    <!-- 폼 시작, 서버에 데이터를 전송하기 위한 POST 방식 -->
    <form name="frm">
        
        <!-- 빵집 이름 입력 필드 -->
        <label for="bakery_name">빵집 이름</label>
        <input type="text" id="bakery_name" name="bakery_name">

        <!-- 리뷰 내용 입력 필드 -->
        <label for="review_contents">리뷰 내용</label>
        <textarea id="review_contents" name="review_contents" rows="5"></textarea>

        <!-- 이미지 업로드 입력 필드, 여러 개의 이미지 선택 가능 -->
        <label for="file">이미지</label>
        <input type="file" id="attachfile" name="attachfile" multiple>

        <!-- 주소 입력 필드 -->
        <label for="address">주소</label>
        <input type="text" id="address" name="address" >

        <!-- 전화번호 입력 필드 -->
        <label for="bakery_phone">전화번호</label>
        <input type="text" id="bakery_phone" name="bakery_phone">

        <!-- 운영시간 입력 필드 -->
        <label for="operating_hours">운영시간</label>
        <input type="text" id="operating_hours" name="operating_hours">

        <!-- 주차 정보 입력 필드 -->
        <label for="parking_info">주차 정보</label>
        <input type="text" id="parking_info" name="parking_info">

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
        <button type="button" onclick="reviewCheck();">게시물 등록</button>
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