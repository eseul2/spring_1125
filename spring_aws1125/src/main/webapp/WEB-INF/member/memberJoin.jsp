<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link href= "<%=request.getContextPath()%>/resources/css/joinStyle.css" type-"text/css" rel="stylesheet" >
<!-- CDN주소 제이쿼리 사용하는주소 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script> 
<script>

// 이메일 형식
const email = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$/i;


//버튼을 눌렀을 때 check함수 작동
function check() {
	
	//유효성 검사하기
	var fm = document.frm;
	
	if(fm.memberid.value == "") {
		alert("아이디를 입력해주세요");
		fm.memberid.focus();  // 커서가 입력안한 해당 자리로 갈수 있도록 
		return;
	}else if(fm.memberpw.value =="") {
		alert("비밀번호를 입력해주세요");
		fm.memberpw.focus(); 
		return;
	}else if(fm.memberpw2.value =="") {
		alert("비밀번호2를 입력해주세요");
		fm.memberpw2.focus(); 
		return;
	}else if(fm.memberpw.value != fm.memberpw2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		fm.memberpw2.value=""; 
		fm.memberpw2.focus(); 
		return;
	}else if(fm.membername.value =="") {
		alert("이름을 입력해주세요");
		fm.membername.focus(); 
		return;
	}else if(fm.memberemail.value =="") {
		alert("이메일을 입력해주세요");
		fm.memberemail.focus(); 
		return;
	}else if(email.test(fm.memberemail.value) == false) {
		alert("이메일 형식이 올바르지 않습니다.");
		fm.memberemail.value=""; 
		fm.memberemail.focus(); 
		return;
	}else if(fm.memberphone.value =="") {
		alert("연락처를 입력해주세요");
		fm.memberphone.focus(); 
		return;
	}else if(fm.memberbirth.value =="") {
		alert("생년월일을 입력해주세요");
		fm.memberbirth.focus(); 
		return;
	}else if(fm.btn.value=="N"){
		alert("아이디를 중복체크를 해주세요");
		fm.memberid.focus(); 
		return;
	} 
	var ans = confirm("저장하시겠습니까?");
	
	if(ans == true) {	
		//해킹을 당할 수 있기 때문에 가상경로를 쓴다. 		가짜경로 형식은 /기능/세부기능.aws 
		fm.action="<%=request.getContextPath()%>/member/memberJoinAction.aws"; 
		fm.method="post";
		fm.submit();	
	}
	return; // 리턴에 값을 안쓰면 그냥 멈춤 종료
}


// 맨처음 시작할 때 중복체크 
$(document).ready(function(){
	
	$("#btn").click(function(){
	//alert("중복체크버튼 클릭");
	
	let memberId = $("#memberid").val();  /* 버튼을 클릭하게되면 이 멤버 아이디 변수에 저장하겠다 */
	if(memberId=="") {
		alert("아이디를 입력해주세요");
		return;
	}
	
	$.ajax({	// ajax형식 자바스크립트와 제이슨을 비동기 통신을 하는 방식이다.
		type : "post",	//전송방식
		url : "<%=request.getContextPath()%>/member/memberIdCheck.aws", 
		dataType : "json",	// json타입은 문서에서 {"키값" : "value값","키값2" : "value값2"}
		data : {"memberId" : memberId },
		success : function(result){	//결과가 넘어와서 성공했을 때 받는 영역
						
			if(result.cnt == 0) {
				alert("사용할 수 있는 아이디입니다.");
				$("#btn").val("Y");
			}else{
				alert("사용할 수 없는 아이디입니다.");
				$("#memberid").val("");		// 입력한 아이디 지우기 입력한 해당 값을 지운다. 
			}
		},
		error : function() {	// 결과가 실패했을 때 받는 영역 
			alert("전송 실패 테스트");
		}		
	});
	});
});

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



<form id="signupForm" name = "frm">
    <label for="userId">아이디</label>
    <input type="text" id="memberid" name="memberid">
    <button type="button" name= "btn" id="btn" value="N">아이디 중복 확인</button>

    <label for="password">비밀번호</label>
    <input type="password" id="memberpw" name="memberpw">
    
    <label for="confirmPassword">비밀번호 확인</label>
    <input type="password" id="memberpw2" name="memberpw2">

    <label for="email">이메일</label>
    <input type="email" id="memberemail" name="memberemail">

    <label for="name">이름</label>
    <input type="text" id="membername" name="membername">

    <label for="birthdate">생년월일</label>
    <input type="date" id="memberbirth" name="memberbirth">

    <label for="phone">핸드폰 번호 (- 제외)</label>
    <input type="number" id="memberphone" name="memberphone">

    <label for="gender">성별</label>
    <select id="membergender" name="membergender">
        <option value="">성별 선택</option>
        <option value="M">남성</option>
        <option value="F">여성</option>
    </select>

    <button type="button" onclick="check()">가입하기</button>
</form>



    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>

</body>
</html>