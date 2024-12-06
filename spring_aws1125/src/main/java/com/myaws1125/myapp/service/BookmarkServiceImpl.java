package com.myaws1125.myapp.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.persistance.BookmarkMapper;


@Service
public class BookmarkServiceImpl implements BookmarkService {
	
	
    private BookmarkMapper bkm;
    
    
	public BookmarkServiceImpl(SqlSession sqlSession) {
		this.bkm = sqlSession.getMapper(BookmarkMapper.class);
	}
	
	
	
    @Override
    public boolean toggleBookmark(int midx, int review_id) {
        // 북마크가 이미 존재하는지 확인
        int existingBookmark = bkm.checkBookmark(midx, review_id);

        if (existingBookmark > 0) {
            // 북마크가 이미 있으면 삭제
        	bkm.deleteBookmark(midx, review_id);
            return false;  // 제거된 경우
        } else {
            // 북마크가 없으면 추가
        	bkm.insertBookmark(midx, review_id);
            return true;  // 추가된 경우
        }
    }

    @Override
    public ArrayList<ReviewVo> getBookmarkedReviews(int midx) {
        return bkm.getBookmarkedReviews(midx);
    }	
    
    


}
