package com.myaws1125.myapp.service;

import java.util.ArrayList;

import com.myaws1125.myapp.domain.ReviewVo;

public interface BookmarkService {
	
	
    public boolean toggleBookmark(int midx, int review_id);  
    public ArrayList<ReviewVo> getBookmarkedReviews(int midx);

}
