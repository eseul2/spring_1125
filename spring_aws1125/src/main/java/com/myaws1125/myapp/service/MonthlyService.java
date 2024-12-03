package com.myaws1125.myapp.service;
import java.util.ArrayList;

import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.MonthlyVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface MonthlyService {
	
	public ArrayList<MonthlyVo> monthlySelectAll(SearchCriteria scri); // ��� �Խù� ��������
	public int monthlyTotalCount(SearchCriteria scri); // ����¡ ó��
	public int monthlyInsert(MonthlyVo monv); // ���ο� �Խñ� �߰��ϱ�
	public MonthlyVo monthlySelectOne(int mbidx); // �󼼳��� �����ֱ�
	public int monthlyViewCntUpdate(int mbidx); // ��ȸ�� �ø���

}
