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
	
	@Autowired /* ���Խ�Ų�� */  
	public MonthlyServiceImpl(SqlSession sqlSession) {
		this.monm = sqlSession.getMapper(MonthlyMapper.class); /* �� �ڿ� Ŭ������ �پ���Ѵ�. */ 
	}
	
	
	
	// �Խñ� ����� �˻� ���ǿ� ���� ��ȸ�ϴ� �޼���
	@Override
	public ArrayList<MonthlyVo> monthlySelectAll(SearchCriteria scri) {
		
		// HashMap ��ü�� �����Ͽ� ����¡, �˻� Ÿ��, Ű���� ���� ������ �߰�
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("startPageNum",(scri.getPage()-1)* scri.getPerPageNum()); // ���� ������ ��ȣ
		hm.put("searchType", scri.getSearchType());  // �˻� ����
		hm.put("keyword", scri.getKeyword()); // �˻� Ű����
		hm.put("perPageNum", scri.getPerPageNum()); // �������� �Խñ� ��
		
		// BoardMapper�� ����ؼ� �˻� ���ǿ� �´� �Խñ� ��� ��ȸ
		ArrayList<MonthlyVo> mlist = monm.monthlySelectAll(hm);
		return mlist;
	}

	
	// �� �Խñ� ���� �˻� ���ǿ� ���� �������� �޼���
	@Override
	public int monthlyTotalCount(SearchCriteria scri) {
		// �˻� ������ ���� BoardMapper���� �� �Խñ� ���� ��ȸ		
		int cnt = monm.monthlyTotalCount(scri);
		return cnt;
	}



	@Override
	public int monthlyInsert(MonthlyVo monv) {
		
		int value = monm.monthlyInsert(monv);
		return value;	
	}
	
	
	
	
	
}
	
	
	
	


