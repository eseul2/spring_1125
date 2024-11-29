package com.myaws1125.myapp.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface BoardMapper {
	
	/* 마이바티스용 메소드. 서비스에 있는 메소드 이름을 동일하게 해서 헷갈리지 않게 한다. */ 
	public ArrayList<BoardVo> boardSelectAll(HashMap<String,Object> hm);
	public int boardTotalCount(SearchCriteria scri);
	public int boardInsert(BoardVo bv); // 새로운 게시글 추가하기

}
