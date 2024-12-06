package com.myaws1125.myapp.service;

import java.util.ArrayList;
import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.domain.SearchCriteria;

public interface ReviewService {
	
	public ArrayList<ReviewVo> reviewSelectAll(SearchCriteria scri); // �Խù� ��ü ��������
	public int reviewTotalCount(SearchCriteria scri); // ��ü ���� �̾Ƴ��� 
	public int reviewInsert(ReviewVo rv); // �Խù� �߰��ϱ�
	public ReviewVo reviewSelectOne(int review_id); // ���� ��Ÿ����
	public int reviewDelete(int review_id); // �����ϱ� ���
	public int reviewUpdate(ReviewVo rv); // �����ϱ�
	public boolean updateBookmark(int memberId, int reviewId, String bookmark); // �ϸ�ũ ������Ʈ
	public ArrayList<ReviewVo> bookmarkReviews(SearchCriteria scri); // �ϸ�ũ�� �Խù��� ��������

}
