<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link href= "<%=request.getContextPath()%>/resources/css/joinStyle.css" type-"text/css" rel="stylesheet" >
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



<form id="signupForm">
    <label for="userId">아이디</label>
    <input type="text" id="userId" name="userId" required>
    <button type="button" onclick="checkUserId()">아이디 중복 확인</button>

    <label for="password">비밀번호</label>
    <input type="password" id="password" name="password" required>
    
    <label for="confirmPassword">비밀번호 확인</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required>

    <label for="email">이메일</label>
    <input type="email" id="email" name="email" required>

    <label for="name">이름</label>
    <input type="text" id="name" name="name" required>

    <label for="birthdate">생년월일</label>
    <input type="date" id="birthdate" name="birthdate" required>

    <label for="phone">핸드폰 번호 (- 제외)</label>
    <input type="tel" id="phone" name="phone" required>

    <label for="gender">성별</label>
    <select id="gender" name="gender" required>
        <option value="">성별 선택</option>
        <option value="male">남성</option>
        <option value="female">여성</option>
    </select>

    <button type="submit">가입하기</button>
</form>





    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>

</body>
</html>