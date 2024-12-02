package com.myaws1125.myapp.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.myaws1125.myapp.domain.CommentVo;
import com.myaws1125.myapp.persistance.CommentMapper;



@Service
public class CommentServiceImpl implements CommentService{
	
	private CommentMapper cm;
	
	public CommentServiceImpl(SqlSession sqlSession) {
		this.cm = sqlSession.getMapper(CommentMapper.class);
	}

	
	
	
	@Override
	public ArrayList<CommentVo> commentSelectAll(int bidx, int block) {
				
		ArrayList<CommentVo> clist =  cm.commentSelectAll(bidx,block);		
		return clist;
	}
	
	
	// ¥Ò±€ ªË¡¶
	@Override
	public int commentDelete(CommentVo cv) {
		
		int value = cm.commentDelete(cv);	
		return value;
	}
	
	
	// ¥Ò±€æ≤±‚
	@Override
	public int commentInsert(CommentVo cv) {
	
		int value = cm.commentInsert(cv);
		return value;
	}
	
	
	@Override
	public int commentTotalCnt(int bidx) {
		int cnt = cm.commentTotalCnt(bidx);
		return cnt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
