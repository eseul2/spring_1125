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
	
	// BoardMapper Ÿ���� ��ü ���� (mybatis�� ����)
	private BoardMapper bm;
	
	@Autowired  // SqlSession ��ü�� ���Թ޴� ������
	public BoardServiceImpl(SqlSession sqlSession) {
		// ���Թ��� sqlSession ��ü�� �̿��Ͽ� BoardMapper�� �ʱ�ȭ
		this.bm = sqlSession.getMapper(BoardMapper.class); /* �� �ڿ� Ŭ������ �پ���Ѵ�. */ 
	}
	
	
	
	// �Խñ� ����� �˻� ���ǿ� ���� ��ȸ�ϴ� �޼���
	@Override
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
		
		// HashMap ��ü�� �����Ͽ� ����¡, �˻� Ÿ��, Ű���� ���� ������ �߰�
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("startPageNum",(scri.getPage()-1)* scri.getPerPageNum()); // ���� ������ ��ȣ
		hm.put("searchType", scri.getSearchType());  // �˻� ����
		hm.put("keyword", scri.getKeyword()); // �˻� Ű����
		hm.put("perPageNum", scri.getPerPageNum()); // �������� �Խñ� ��
		
		// BoardMapper�� ����ؼ� �˻� ���ǿ� �´� �Խñ� ��� ��ȸ
		ArrayList<BoardVo> blist = bm.boardSelectAll(hm);
		return blist;
	}

	
	// �� �Խñ� ���� �˻� ���ǿ� ���� �������� �޼���
	@Override
	public int boardTotalCount(SearchCriteria scri) {
		// �˻� ������ ���� BoardMapper���� �� �Խñ� ���� ��ȸ		
		int cnt = bm.boardTotalCount(scri);
		return cnt;
	}
}