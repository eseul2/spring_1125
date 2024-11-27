<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>빵집찾기 관리자 글쓰기</title>
<link href= "<%=request.getContextPath()%>/resources/css/style.css" type-"text/css" rel="stylesheet" >
</head>
<body>



<!-- 제품 정보와 이미지를 포함하는 컨테이너 -->
<div class="product-container">
  <!-- 오른쪽 제품 정보를 담은 영역 -->
  <div class="product-info">
    <!-- 제품 제목 -->
    <h1 class="product-title">메리노 코튼</h1>
    
  
    <!-- 제품 설명 -->
    <p class="product-description">
      [해외배송 가능한 상품] ≈ 50g, 120m, 19가지색, 메리노울 30%, 면 70%
    </p>
    <hr style="border-bottom:0.2px solid lightgray; margin-bottom: 10px;">

    <!-- 제품 세부 정보를 담은 영역 -->
    <div class="product-details">
      <!-- 미니샵 정보 -->
      <p><span class="label">미니샵:</span> <span class="shop-name">씨비 SEVY</span></p>
      <!-- 판매 가격 -->
      <p><span class="label">판매가:</span> 9,000원</p>
      <!-- 브랜드 정보 -->
      <p><span class="label">브랜드:</span> 씨비</p>
      <!-- 배송비 정보 -->
      <p><span class="label">배송비:</span> 3,000원 (30,000원 이상 구매 시 무료)</p>
      <!-- 색상 선택 드롭다운 메뉴 -->
      <p><span class="label">색상 (1볼):</span> 
        <select>
          <option>필수 옵션을 선택해 주세요</option>
          <!-- 색상 옵션들이 추가될 부분 -->
        </select>
      </p>
    </div>
  </div>
</div>

</body>
</html>