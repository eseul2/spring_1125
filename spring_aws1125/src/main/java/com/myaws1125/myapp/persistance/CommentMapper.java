package com.myaws1125.myapp.persistance;

import java.util.ArrayList;

import com.myaws1125.myapp.domain.CommentVo;

/*마이바티스용 메서드*/ 
public interface CommentMapper {
	
	/* 마이바티스용 메소드. 서비스에 있는 메소드 이름을 동일하게 해서 헷갈리지 않게 한다. */ 
	public ArrayList<CommentVo> commentSelectAll(int bidx, int block);
	public int commentDelete(CommentVo cv); // 댓글 삭제
	public int commentInsert(CommentVo cv); // 댓글 쓰기
	public int commentTotalCnt(int bidx);

}
