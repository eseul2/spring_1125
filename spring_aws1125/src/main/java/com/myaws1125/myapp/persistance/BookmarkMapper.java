package com.myaws1125.myapp.persistance;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.myaws1125.myapp.domain.ReviewVo;

public interface BookmarkMapper {
	
	
    // 북마크 추가
    public void insertBookmark(@Param("midx") int midx, @Param("review_id") int review_id);
    
    // 북마크 삭제
    public void deleteBookmark(@Param("midx") int midx, @Param("review_id") int review_id);
    
    // 북마크 여부 확인
    public int checkBookmark(@Param("midx") int midx, @Param("review_id") int review_id);
    
    // 찜한 게시물 목록 조회
    public ArrayList<ReviewVo> getBookmarkedReviews(int midx);	
	
	

	
	
	
}	


