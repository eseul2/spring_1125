package com.myaws1125.myapp.service;

import java.util.ArrayList;
import com.myaws1125.myapp.domain.CommentVo;

public interface CommentService {
	
	public  ArrayList<CommentVo> commentSelectAll(int bidx, int block) ;
	public int commentDelete(CommentVo cv); // ��ۻ���
	public int commentInsert(CommentVo cv); // ��۾���
	public int commentTotalCnt(int bidx); // ��� ���� 

}
