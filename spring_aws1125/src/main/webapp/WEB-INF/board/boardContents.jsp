<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글 내용</title>
<link href= "<%=request.getContextPath()%>/resources/css/boardContentsStyle.css" type-"text/css" rel="stylesheet" >
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
			<li><a href="/this-month-bread">이달의 빵</a></li>
			<li><a href ="<%=request.getContextPath()%>/board/boardList.aws">자유게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/member/memberLogin.aws">로그인</a></li>
		</ul>
	</nav>
<div class="separator"></div>
</header>



 <!-- 글 상세보기 박스 -->
    <div class="post-box">
        <!-- 제목 -->
        <h1 class="post-title">자유게시판 글 제목</h1>
        
        <!-- 작성자, 작성일 -->
        <div class="post-meta">
            작성자: 홍길동 | 작성일: 2024-11-25
        </div>

        <!-- 구분선 -->
        <div class="separator"></div>

        <!-- 수정, 삭제 링크 -->
        <div class="edit-delete">
            <a href="<%=request.getContextPath()%>/board/boardModify.aws">수정</a>
            <a href="deletePost.jsp?postId=1" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
        </div>

        <!-- 글 내용 -->
        <div class="post-content">
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
			여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
            여기는 글 내용입니다. 게시판 글의 내용을 보여주는 부분입니다. 텍스트를 하드코딩으로 넣어서 화면을 확인해보세요.
        </div>

        <!-- 목록으로 돌아가기 링크 -->
        <div class="post-list-link">
           <a href="<%=request.getContextPath()%>/board/boardList.aws">목록으로</a>
        </div>
        

        <!-- 두 번째 구분선 -->
        <div class="separator"></div>

        <!-- 댓글 박스 -->
        <div class="comment-box">
            <h3>댓글</h3>
            
            <!-- 댓글 리스트 -->
            <div class="comment-item">
                <div class="comment-author">댓글작성자1</div>
                <div class="comment-date">2024-11-25</div>
                <div class="comment-content">첫 번째 댓글 내용입니다.</div>
                <a href="deleteComment.jsp?commentId=1&postId=1" class="comment-delete">삭제</a>
            </div>
            <div class="comment-item">
                <div class="comment-author">댓글작성자2</div>
                <div class="comment-date">2024-11-25</div>
                <div class="comment-content">두 번째 댓글 내용입니다.</div>
                <a href="deleteComment.jsp?commentId=2&postId=1" class="comment-delete">삭제</a>
            </div>

            <!-- 댓글 입력 폼 -->
            <form action="addComment.jsp" method="post">
                <input type="hidden" name="postId" value="1">
                <textarea name="commentContent" rows="4" placeholder="댓글을 작성하세요..." required></textarea>
                <button type="submit">댓글 작성</button>
            </form>
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