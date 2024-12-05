package com.myaws1125.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.persistance.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	
	// ReviewMapper 타입의 객체 생성 (mybatis와 연동)
	private ReviewMapper rm;
	
	@Autowired  // SqlSession 객체를 주입받는 생성자
	public ReviewServiceImpl(SqlSession sqlSession) {
		// 주입받은 sqlSession 객체를 이용하여 BoardMapper를 초기화
		this.rm = sqlSession.getMapper(ReviewMapper.class); /* 꼭 뒤에 클래스가 붙어야한다. */ 
	}
	
	
	
	
	
	// 게시글 목록을 검색 조건에 따라 조회하는 메서드
	@Override
	public ArrayList<ReviewVo> reviewSelectAll(SearchCriteria scri) {
		
		// HashMap 객체를 생성하여 페이징, 검색 타입, 키워드 등의 조건을 추가
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("startPageNum",(scri.getPage()-1)* scri.getPerPageNum()); // 시작 페이지 번호
		hm.put("searchType", scri.getSearchType());  // 검색 유형
		hm.put("keyword", scri.getKeyword()); // 검색 키워드
		hm.put("perPageNum", scri.getPerPageNum()); // 페이지당 게시글 수
		hm.put("area", scri.getArea());  // 지역 정보 추가
		
		// ReviewMapper를 사용해서 검색 조건에 맞는 게시글 목록 조회
		ArrayList<ReviewVo> rlist = rm.reviewSelectAll(hm);
		return rlist;
	}

	
	// 총 게시글 수를 검색 조건에 따라 가져오는 메서드
	@Override
	public int reviewTotalCount(SearchCriteria scri) {
		// 검색 조건을 통해 BoardMapper에서 총 게시글 수를 조회		
		int cnt = rm.reviewTotalCount(scri);
		return cnt;
	}


	// 게시물 추가하기
	@Override
	public int reviewInsert(ReviewVo rv) {
		
		int value = rm.reviewInsert(rv);
		return value;
	}



	// 내용 보여주기
	@Override
	public ReviewVo reviewSelectOne(int review_id) {
		
		ReviewVo rv = rm.reviewSelectOne(review_id);
		return rv;
	}





	@Override
	public int reviewDelete(int review_id) {

		int value = rm.reviewDelete(review_id);
		return value;
	}

	// 수정하기
	@Override
	public int reviewUpdate(ReviewVo rv) {
		
		int value = rm.reviewUpdate(rv); // rv값 넘겨주기 
		return value;
	}

}
