package com.myaws1125.myapp.service;

import java.util.ArrayList;
import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface ReviewService {
	
	public ArrayList<ReviewVo> reviewSelectAll(SearchCriteria scri); // �Խù� ��ü ��������
	public int reviewTotalCount(SearchCriteria scri); // ��ü ���� �̾Ƴ��� 
	public int reviewInsert(ReviewVo rv); // �Խù� �߰��ϱ�
	public ReviewVo reviewSelectOne(int review_id); // ���� ��Ÿ����
	

}
