package com.myaws1125.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws1125.myapp.domain.MonthlyVo;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.persistance.MonthlyMapper;

@Service
public class MonthlyServiceImpl implements MonthlyService{
	
	
	private MonthlyMapper monm;
	
	@Autowired /* 주입시킨다 */  
	public MonthlyServiceImpl(SqlSession sqlSession) {
		this.monm = sqlSession.getMapper(MonthlyMapper.class); /* 꼭 뒤에 클래스가 붙어야한다. */ 
	}
	
	
	
	// 게시글 목록을 검색 조건에 따라 조회하는 메서드
	@Override
	public ArrayList<MonthlyVo> monthlySelectAll(SearchCriteria scri) {
		
		// HashMap 객체를 생성하여 페이징, 검색 타입, 키워드 등의 조건을 추가
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("startPageNum",(scri.getPage()-1)* scri.getPerPageNum()); // 시작 페이지 번호
		hm.put("searchType", scri.getSearchType());  // 검색 유형
		hm.put("keyword", scri.getKeyword()); // 검색 키워드
		hm.put("perPageNum", scri.getPerPageNum()); // 페이지당 게시글 수
		
		// BoardMapper를 사용해서 검색 조건에 맞는 게시글 목록 조회
		ArrayList<MonthlyVo> mlist = monm.monthlySelectAll(hm);
		return mlist;
	}

	
	// 총 게시글 수를 검색 조건에 따라 가져오는 메서드
	@Override
	public int monthlyTotalCount(SearchCriteria scri) {
		// 검색 조건을 통해 BoardMapper에서 총 게시글 수를 조회		
		int cnt = monm.monthlyTotalCount(scri);
		return cnt;
	}



	@Override
	public int monthlyInsert(MonthlyVo monv) {
		
		int value = monm.monthlyInsert(monv);
		return value;	
	}
	
	
	
	
	
}
	
	
	
	


