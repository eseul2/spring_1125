package com.myaws1125.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.persistance.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	// BoardMapper 타입의 객체 생성 (mybatis와 연동)
	private BoardMapper bm;
	
	@Autowired  // SqlSession 객체를 주입받는 생성자
	public BoardServiceImpl(SqlSession sqlSession) {
		// 주입받은 sqlSession 객체를 이용하여 BoardMapper를 초기화
		this.bm = sqlSession.getMapper(BoardMapper.class); /* 꼭 뒤에 클래스가 붙어야한다. */ 
	}
	
	
	
	// 게시글 목록을 검색 조건에 따라 조회하는 메서드
	@Override
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
		
		// HashMap 객체를 생성하여 페이징, 검색 타입, 키워드 등의 조건을 추가
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("startPageNum",(scri.getPage()-1)* scri.getPerPageNum()); // 시작 페이지 번호
		hm.put("searchType", scri.getSearchType());  // 검색 유형
		hm.put("keyword", scri.getKeyword()); // 검색 키워드
		hm.put("perPageNum", scri.getPerPageNum()); // 페이지당 게시글 수
		
		// BoardMapper를 사용해서 검색 조건에 맞는 게시글 목록 조회
		ArrayList<BoardVo> blist = bm.boardSelectAll(hm);
		return blist;
	}

	
	// 총 게시글 수를 검색 조건에 따라 가져오는 메서드
	@Override
	public int boardTotalCount(SearchCriteria scri) {
		// 검색 조건을 통해 BoardMapper에서 총 게시글 수를 조회		
		int cnt = bm.boardTotalCount(scri);
		return cnt;
	}
}