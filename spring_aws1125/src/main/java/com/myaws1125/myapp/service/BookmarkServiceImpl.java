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
        // �ϸ�ũ�� �̹� �����ϴ��� Ȯ��
        int existingBookmark = bkm.checkBookmark(midx, review_id);

        if (existingBookmark > 0) {
            // �ϸ�ũ�� �̹� ������ ����
        	bkm.deleteBookmark(midx, review_id);
            return false;  // ���ŵ� ���
        } else {
            // �ϸ�ũ�� ������ �߰�
        	bkm.insertBookmark(midx, review_id);
            return true;  // �߰��� ���
        }
    }

    @Override
    public ArrayList<ReviewVo> getBookmarkedReviews(int midx) {
        return bkm.getBookmarkedReviews(midx);
    }	
    
    


}
