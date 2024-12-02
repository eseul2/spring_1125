package com.myaws1125.myapp.service;

import java.util.ArrayList;
import com.myaws1125.myapp.domain.CommentVo;

public interface CommentService {
	
	public  ArrayList<CommentVo> commentSelectAll(int bidx, int block) ;
	public int commentDelete(CommentVo cv); // ´ñ±Û»èÁ¦
	public int commentInsert(CommentVo cv); // ´ñ±Û¾²±â
	public int commentTotalCnt(int bidx); // ´ñ±Û °¹¼ö 

}
