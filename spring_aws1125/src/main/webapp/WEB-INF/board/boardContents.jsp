<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.myaws1125.myapp.domain.BoardVo" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!--  request.getAttribute()는 서블릿이나 다른 JSP 페이지에서 전달된 객체를 가져올 때 사용하는 메서드 -->
<%-- <% 
BoardVo bv = (BoardVo)request.getAttribute("bv");   //강제형변환  양쪽형을 맞춰준다 
 
String memberName="";
if(session.getAttribute("memberName") != null) {
	memberName = (String)session.getAttribute("memberName");
}
int midx =0;
if(session.getAttribute("midx") != null) {
	midx = Integer.parseInt(session.getAttribute("midx").toString());
} 
%> --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글 내용</title>
<link href= "<%=request.getContextPath()%>/resources/css/boardContentsStyle.css" type-"text/css" rel="stylesheet" >
</head>
<!-- 제이쿼리 cdn 주소 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script> 
<script>

//파일 이름을 받아서 해당 파일이 이미지 파일인지 확인하는 역할
function checkImageType(fileName) {
	var pattern = /jpg$|gif$|png$|jpeg$/i;   // 파일 확장자가 jpg, gif, png, jpeg로 끝나는지 검사하는 자바 정규표현식
	return fileName.match(pattern); 		// 정규표현식과 일치하는지 확인
}


function getOriginalFileName(fileName) {  // 원본 파일이름 추출
	var idx = fileName.lastIndexOf("_")+1; // 마지막 "_" 문자의 위치를 찾고 그 다음 위치를 저장
	return fileName.substr(idx);   // idx 위치부터 문자열의 끝까지 반환
}


//글자를 잘라내서 원본 파일을 다운받도록 한다. 
function getImageLink(fileName) {
	var front = fileName.substr(0,12); // 앞에서 12글자까지 가져온다.	
	var end = fileName.substr(14); // 14번째 인덱스부터 끝까지 가져온다.
	return front+end; // 두 부분을 이어 붙여 반환
}


function download(){
	// 주소사이에 s-는 빼고 
	var downloadImage = getImageLink("${bv.filename}");
	var downLink = "${pageContext.request.contextPath}/board/displayFile.aws?fileName="+downloadImage+"&down=1";
	return downLink;
}



function commentDel(cidx) {  // 버튼을 눌렀을때 삭제 기능
	
	let ans = confirm("삭제하시겠습니까?");
	
	if(ans == true) {
		
		$.ajax({	// ajax 형식
			type : "get",	//전송방식 겟방식
			url : "${pageContext.request.contextPath}/comment/"+cidx+"/commentDeleteAction.aws", 
			dataType : "json",	// json타입은 문서에서 {"키값" : "value값","키값2" : "value값2"}
			success : function(result){	//결과가 넘어와서 성공했을 때 받는 영역
				
				//alert("전송 성공 테스트");
				//alert(+result.value);
			 	$.boardCommentList();	
			},
			error : function() {	// 결과가 실패했을 때 받는 영역 
				alert("전송 실패1");
			}	
		});
	}
	return;
}




//제이쿼리로 함수 만들기 
$.boardCommentList = function(){  // 보드코멘트리스트 라는 이름의 함수다.
	
let block = $("#block").val();	
	
$.ajax({	// ajax 형식
	type : "get",	//전송방식 겟방식
	url : "${pageContext.request.contextPath}/comment/${bv.bidx}/"+block+"/commentList.aws", 
	dataType : "json",	// json타입은 문서에서 {"키값" : "value값","키값2" : "value값2"}
	success : function(result){	//결과가 넘어와서 성공했을 때 받는 영역
		
	var strTr = "";
	$(result.clist).each(function(){
			
		var btnn ="";
			
			//현재로그인 사람과 댓글쓴 사람의 번호가 같을때만 나타내준다
			if (this.midx == "${midx}") {
				if (this.delyn=="N"){
					btnn= "<button type='button' onclick='commentDel("+this.cidx+");'>삭제</button>";
				}			
			}
			strTr = strTr + "<tr>"
			+"<td>"+this.cidx+"</td>"
			+"<td>"+this.cwriter+"</td>"
			+"<td class='content'>"+this.ccontents+"</td>"
			+"<td>"+this.writeday+"</td>"
			+"<td>"+btnn+"</td>"
			+"</tr>";					
		});		       
		
		var str  = "<table class='replyTable'>"
			+"<tr>"
			+"<th></th>"
			+"<th>작성자</th>"
			+"<th></th>"
			+"<th>날짜</th>"
			+"<th></th>"
			+"</tr>"+strTr+"</table>";		
			
		$("#commentListView").html(str);
		
		
		if(result.moreView == "N") {
			$("#morebtn").css("display","none"); // 감춘다.
		}else {
			$("#morebtn").css("display","block"); // 보여준다
		}
		
		if(result.nextBlock>block) {
			$("#block").val(result.nextBlock);
		}
		
	},
	error : function() {	// 결과가 실패했을 때 받는 영역 
		alert("전송 실패2");
	}	
 });
}




// 파일 다운로드하기
$(document).ready(function(){
	
	$("#dUrl").html(getOriginalFileName("${bv.filename}"));
	
	$("#dUrl").click(function(){
		$("#dUrl").attr("href",download());
		return;
	});
	
	$.boardCommentList();

	
	
$("#cmtBtn").click(function(){   //댓글쓰기 유효성 검사 
	alert("들어왔나?");
	let midx = "${midx}";
	//alert(loginCheck);
	if (midx == "" || midx == "null" || midx == null || midx == 0) {
		alert("로그인을 해주세요");
		return;
	}
	
	let cwriter = $("#cwriter").val();
	let ccontents = $("#ccontents").val();
	
	if(ccontents =="") {
		alert("내용을 입력해주세요");
		$("#ccontents").focus();
		return;
	}
	
	$.ajax({	// ajax 형식
		type : "post",	//전송방식 겟방식
		url : "${pageContext.request.contextPath}/comment/commentWriteAction.aws", 
		data : {"cwriter" : cwriter, 
			    "ccontents" : ccontents, 
			    "bidx" :"${bv.bidx}",
			    "midx" : "${midx}"
			    },
		dataType : "json",	// json타입은 문서에서 {"키값" : "value값","키값2" : "value값2"}
		success : function(result){	//결과가 넘어와서 성공했을 때 받는 영역	
			//alert("전송 성공 테스트");
			//var str = "("+result.value+")";
			//alert(str);
			if(result.value == 1) {
				$("#ccontents").val("");    // 입력이 성공하면 댓글창을 비워라 
				$("#block").val(1);
			}
			$.boardCommentList();
		},
		error : function() {	// 결과가 실패했을 때 받는 영역 
			alert("전송 실패4");
		}	
	});
});

$("#more").click(function(){
	$.boardCommentList();
})
});	 
	
	

</script>

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
			<li><a href ="<%=request.getContextPath()%>/bookmark/bookmarkList.aws">🤍</a></li>
		</ul>
	</nav>
<div class="separator"></div>
</header>



 <!-- 글 상세보기 박스 -->
    <div class="post-box">
        <!-- 제목 -->
        <h1 class="post-title">${bv.subject}</h1>
        
        <!-- 작성자, 작성일 -->
        <div class="post-meta">
            작성자: ${bv.writer} | 작성일: ${bv.writeday}
        </div>

        <!-- 구분선 -->
        <div class="separator"></div>
        
		<!-- 조회수 -->
    	<div class="view-count">
        	조회수: ${bv.viewcnt}
    	</div>

        <!-- 수정, 삭제 링크 -->
        <div class="edit-delete">
            <a href="<%=request.getContextPath()%>/board/boardModify.aws?bidx=${bv.bidx}">수정</a>
            <a href="<%=request.getContextPath()%>/board/boardDelete.aws?bidx=${bv.bidx}">삭제</a>
        </div>

        <!-- 글 내용 -->
        <div class="post-content">
           ${bv.contents}
        </div>
        
	
<c:if test="${!empty bv.filename}">
    <img src="${pageContext.request.contextPath}/board/displayFile.aws?fileName=${bv.filename}" class="file-image">
    <P>
        <a id="dUrl" href="#" class="fileDown">첨부파일 다운로드</a>
    </P>
</c:if>
	

        <!-- 목록으로 돌아가기 링크 -->
        <div class="post-list-link">
           <a href="<%=request.getContextPath()%>/board/boardList.aws">목록으로</a>
        </div>
        

        <!-- 두 번째 구분선 -->
        <div class="separator"></div>

        <!-- 댓글 박스 -->
        <div class="comment-box">
            <h3>댓글</h3>
             <div id = "commentListView"></div>

	<form name="frm">
		<p class="commentWriter"><input type="text" id="cwriter" name="cwriter" value="${memberName}" readonly="readonly" style="width:80px;border:0px;">
		</p>	
		<input type="text" id="ccontents" name="ccontents">
		<button type="button" id="cmtBtn" class="replyBtn">댓글쓰기</button>
	</form>
        </div>
    </div>
    

<input type="hidden" id="block" value="1">
<div id="morebtn">
	<button type="button" id="more">더보기</button>
</div>   
    
    
    
    <!-- 푸터 영역 (페이지 끝부분에 추가) -->
    <footer class="custom-footer">
        <div class="footer-content">
            <p>&copy; 2024 빵지순례 웹사이트. 모든 권리 보유.</p>
        </div>
    </footer>    


</body>
</html>