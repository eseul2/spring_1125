package com.myaws1125.myapp.persistance;

import java.util.ArrayList;

import com.myaws1125.myapp.domain.CommentVo;

/*���̹�Ƽ���� �޼���*/ 
public interface CommentMapper {
	
	/* ���̹�Ƽ���� �޼ҵ�. ���񽺿� �ִ� �޼ҵ� �̸��� �����ϰ� �ؼ� �򰥸��� �ʰ� �Ѵ�. */ 
	public ArrayList<CommentVo> commentSelectAll(int bidx, int block);
	public int commentDelete(CommentVo cv); // ��� ����
	public int commentInsert(CommentVo cv); // ��� ����
	public int commentTotalCnt(int bidx);

}
