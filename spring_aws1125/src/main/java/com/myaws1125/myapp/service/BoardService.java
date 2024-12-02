package com.myaws1125.myapp.service;

import java.util.ArrayList;
import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface BoardService {

	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri);
	public int boardTotalCount(SearchCriteria scri);
	public int boardInsert(BoardVo bv); // 새로운 게시글 추가하기
	public BoardVo boardSelectOne(int bidx); // 상세내용 보여주기
	public int boardViewCntUpdate(int bidx); // 조회수 올리기 
	public int boardDelete(int bidx, int midx, String password); // 삭제하기 기능
	public int boardUpdate(BoardVo bv); // 수정하기 기능 

}
