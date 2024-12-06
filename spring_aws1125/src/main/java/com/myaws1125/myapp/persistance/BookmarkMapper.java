package com.myaws1125.myapp.persistance;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.myaws1125.myapp.domain.ReviewVo;

public interface BookmarkMapper {
	
	
    // �ϸ�ũ �߰�
    public void insertBookmark(@Param("midx") int midx, @Param("review_id") int review_id);
    
    // �ϸ�ũ ����
    public void deleteBookmark(@Param("midx") int midx, @Param("review_id") int review_id);
    
    // �ϸ�ũ ���� Ȯ��
    public int checkBookmark(@Param("midx") int midx, @Param("review_id") int review_id);
    
    // ���� �Խù� ��� ��ȸ
    public ArrayList<ReviewVo> getBookmarkedReviews(int midx);	
	
	

	
	
	
}	


