package com.myaws1125.myapp.service;

import java.util.ArrayList;
import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface ReviewService {
	
	public ArrayList<ReviewVo> reviewSelectAll(SearchCriteria scri); // 게시물 전체 가져오기
	public int reviewTotalCount(SearchCriteria scri); // 전체 갯수 뽑아내기 
	public int reviewInsert(ReviewVo rv); // 게시물 추가하기
	public ReviewVo reviewSelectOne(int review_id); // 내용 나타내기
	

}
