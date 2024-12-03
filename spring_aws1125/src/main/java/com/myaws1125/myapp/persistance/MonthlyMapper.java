package com.myaws1125.myapp.persistance;
import java.util.ArrayList;
import java.util.HashMap;
import com.myaws1125.myapp.domain.MonthlyVo;
import com.myaws1125.myapp.domain.SearchCriteria;


public interface MonthlyMapper {
	
	public ArrayList<MonthlyVo> monthlySelectAll(HashMap<String,Object> hm); // ��� �Խù� ��������
	public int monthlyTotalCount(SearchCriteria scri); // ����¡ ó��
	public int monthlyInsert(MonthlyVo monv); // ���ο� �Խñ� �߰��ϱ�
	public MonthlyVo monthlySelectOne(int mbidx); // �󼼳��� �����ֱ�
	public int monthlyViewCntUpdate(int mbidx); // ��ȸ�� �ø���

}
