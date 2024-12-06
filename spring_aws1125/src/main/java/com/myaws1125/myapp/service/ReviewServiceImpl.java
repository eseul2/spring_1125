package com.myaws1125.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.persistance.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	
	// ReviewMapper Ÿ���� ��ü ���� (mybatis�� ����)
	private ReviewMapper rm;
	
	@Autowired  // SqlSession ��ü�� ���Թ޴� ������
	public ReviewServiceImpl(SqlSession sqlSession) {
		// ���Թ��� sqlSession ��ü�� �̿��Ͽ� BoardMapper�� �ʱ�ȭ
		this.rm = sqlSession.getMapper(ReviewMapper.class); /* �� �ڿ� Ŭ������ �پ���Ѵ�. */ 
	}
	
	
	
	
	
	// �Խñ� ����� �˻� ���ǿ� ���� ��ȸ�ϴ� �޼���
	@Override
	public ArrayList<ReviewVo> reviewSelectAll(SearchCriteria scri) {
		
		// HashMap ��ü�� �����Ͽ� ����¡, �˻� Ÿ��, Ű���� ���� ������ �߰�
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("startPageNum",(scri.getPage()-1)* scri.getPerPageNum()); // ���� ������ ��ȣ
		hm.put("searchType", scri.getSearchType());  // �˻� ����
		hm.put("keyword", scri.getKeyword()); // �˻� Ű����
		hm.put("perPageNum", scri.getPerPageNum()); // �������� �Խñ� ��
		hm.put("area", scri.getArea());  // ���� ���� �߰�
		
		// ReviewMapper�� ����ؼ� �˻� ���ǿ� �´� �Խñ� ��� ��ȸ
		ArrayList<ReviewVo> rlist = rm.reviewSelectAll(hm);
		return rlist;
	}

	
	// �� �Խñ� ���� �˻� ���ǿ� ���� �������� �޼���
	@Override
	public int reviewTotalCount(SearchCriteria scri) {
		// �˻� ������ ���� BoardMapper���� �� �Խñ� ���� ��ȸ		
		int cnt = rm.reviewTotalCount(scri);
		return cnt;
	}


	// �Խù� �߰��ϱ�
	@Override
	public int reviewInsert(ReviewVo rv) {
		
		int value = rm.reviewInsert(rv);
		return value;
	}



	// ���� �����ֱ�
	@Override
	public ReviewVo reviewSelectOne(int review_id) {
		
		ReviewVo rv = rm.reviewSelectOne(review_id);
		return rv;
	}





	@Override
	public int reviewDelete(int review_id) {

		int value = rm.reviewDelete(review_id);
		return value;
	}

	// �����ϱ�
	@Override
	public int reviewUpdate(ReviewVo rv) {
		
		int value = rm.reviewUpdate(rv); // rv�� �Ѱ��ֱ� 
		return value;
	}




	// �ϸ�ũ ������Ʈ
	@Override
	public boolean updateBookmark(int memberId, int reviewId, String bookmark) {
        
		int result = rm.updateBookmark(memberId, reviewId, bookmark);
        return result > 0; // ������Ʈ�� ���� ���� 1 �̻��̸� ����
	}
	
	
	// �ϸ�ũ�� �Խù��� ��������
	@Override
	public ArrayList<ReviewVo> bookmarkReviews(SearchCriteria scri) {
		
		// HashMap ��ü�� �����Ͽ� ����¡, �˻� Ÿ��, Ű���� ���� ������ �߰�
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("startPageNum",(scri.getPage()-1)* scri.getPerPageNum()); // ���� ������ ��ȣ
		hm.put("searchType", scri.getSearchType());  // �˻� ����
		hm.put("keyword", scri.getKeyword()); // �˻� Ű����
		hm.put("perPageNum", scri.getPerPageNum()); // �������� �Խñ� ��
		hm.put("area", scri.getArea());  // ���� ���� �߰�
		
		// ReviewMapper�� ����ؼ� �˻� ���ǿ� �´� �Խñ� ��� ��ȸ
		ArrayList<ReviewVo> rrlist = rm.bookmarkReviews(hm);
		return rrlist;
	}






}
