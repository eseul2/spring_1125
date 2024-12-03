package com.myaws1125.myapp.service;
import java.util.ArrayList;

import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.MonthlyVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface MonthlyService {
	
	public ArrayList<MonthlyVo> monthlySelectAll(SearchCriteria scri); // 모든 게시물 가져오기
	public int monthlyTotalCount(SearchCriteria scri); // 페이징 처리
	public int monthlyInsert(MonthlyVo monv); // 새로운 게시글 추가하기
	public MonthlyVo monthlySelectOne(int mbidx); // 상세내용 보여주기
	public int monthlyViewCntUpdate(int mbidx); // 조회수 올리기

}
