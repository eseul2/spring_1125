package com.myaws1125.myapp.service;

import java.util.ArrayList;
import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface BoardService {

	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri);
	public int boardTotalCount(SearchCriteria scri);

}
