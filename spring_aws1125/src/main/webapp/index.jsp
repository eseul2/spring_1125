<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>

<%-- <!-- 값이 비어있지 않으면 -->
<c:if test="${!empty midx}">
${memberName}&nbsp;
<a href='${pageContext.request.contextPath}/member/memberLogout.aws'>님, 로그아웃</a> 
</c:if>
<br> --%>


<a href ="<%=request.getContextPath()%>/member/main.aws">메인 페이지</a>
<br>
<a href ="<%=request.getContextPath()%>/board/boardContents.aws">자유게시판 내용</a>
<br>

<a href ="<%=request.getContextPath()%>/board/test.aws">내용</a>

</body>
</html>



