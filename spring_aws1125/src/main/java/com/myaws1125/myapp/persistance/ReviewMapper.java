package com.myaws1125.myapp.persistance;

import java.util.ArrayList;
import java.util.HashMap;

import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface ReviewMapper {
	
	public ArrayList<ReviewVo> reviewSelectAll(HashMap<String,Object> hm); // �Խù� ��ü ��������
	public int reviewTotalCount(SearchCriteria scri); // ��ü ���� �̾Ƴ���
	public int reviewInsert(ReviewVo rv); // �Խù� �߰��ϱ�
	public ReviewVo reviewSelectOne(int review_id); // ���� ��Ÿ����
	public int reviewDelete(int review_id); // �����ϱ� ���
	public int reviewUpdate(ReviewVo rv); // �����ϱ�

}
