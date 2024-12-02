package com.myaws1125.myapp.service;

import java.util.ArrayList;
import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface BoardService {

	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri);
	public int boardTotalCount(SearchCriteria scri);
	public int boardInsert(BoardVo bv); // ���ο� �Խñ� �߰��ϱ�
	public BoardVo boardSelectOne(int bidx); // �󼼳��� �����ֱ�
	public int boardViewCntUpdate(int bidx); // ��ȸ�� �ø��� 
	public int boardDelete(int bidx, int midx, String password); // �����ϱ� ���
	public int boardUpdate(BoardVo bv); // �����ϱ� ��� 

}
