<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link href= "<%=request.getContextPath()%>/resources/css/loginStyle.css" type-"text/css" rel="stylesheet" >

<script>

//아이디 비밀번호 유효성 검사 
function check() {
	// 이름으로 객체찾기
	let memberid = document.getElementsByName("memberid"); //배열처럼 방에 들어간다. 
	let memberpw = document.getElementsByName("memberpw");

	if(memberid[0].value=="") {
		alert("아이디를 입력해주세요");
		memeberid[0].focus();
		return;
	}else if(memberpw[0].value=="") {
		alert("비밀번호를 입력해주세요");
		memberpw[0].focus();
		return;
	}
	var fm = document.frm;
	fm.action="<%=request.getContextPath()%>/member/memberLoginAction.aws";  //가상경로 지정. 액션은 처리한다는 의미 
	fm.method="post"; // 메소드는 포스트 방식으로 할거다
	fm.submit(); //서브밋을 사영해소 이동시틸거다
	
	return;
}
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
			<li><a href="<%=request.getContextPath()%>/member/memberLogin.aws">로그인</a></li>
			<li><a href ="<%=request.getContextPath()%>/bookmark/bookmarkList.aws">🤍</a></li>
		</ul>
	</nav>
<div class="separator"></div>
</header>

		<!-- 로그인 하기 -->
    <div class="container">
        <h1 class="site-name">로그인하기</h1>
        <div class="login-box">
            <form name="frm" >
                <input type="text" name="memberid" placeholder="아이디">
                <input type="password" name="memberpw" placeholder="비밀번호">
                <button type="button" value="로그인하기" onclick="check();">로그인 하기</button>
            </form>
            <div class="links">
                <a href="/find-id">아이디찾기</a> |
                <a href="/find-password">비밀번호 찾기</a> |
                <a href="<%=request.getContextPath()%>/member/memberJoin.aws">회원가입</a>
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