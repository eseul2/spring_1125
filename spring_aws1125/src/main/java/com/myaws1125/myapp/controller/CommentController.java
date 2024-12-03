package com.myaws1125.myapp.controller;

import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myaws1125.myapp.domain.CommentVo;
import com.myaws1125.myapp.service.CommentService;
import com.myaws1125.myapp.util.UserIp;

@RestController
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired(required = false) 
	private UserIp userIp;
	
	
	@RequestMapping(value="/{bidx}/{block}/commentList.aws")
	public JSONObject commentList(
			@PathVariable("bidx") int bidx, //�̰� ���ؼ� �� bidx���� ���� �� �ִ�.	
			@PathVariable("block") int block
			){
		
		String moreView="";
		int nextBlock=0; // ��������
		int cnt = commentService.commentTotalCnt(bidx);
		
		if(cnt > block*15) {
			moreView = "Y";
			nextBlock = block+1; // �ϳ��� �������ֱ�	
		}else {
			moreView = "N";
			nextBlock = block;
		}
	
		ArrayList<CommentVo> clist = commentService.commentSelectAll(bidx,block);
		
		JSONObject js = new JSONObject();
		js.put("clist", clist);   //���̽��� ��Ƽ� ȭ�鿡 ������ ���ſ��� 
		js.put("moreView", moreView);
		js.put("nextBlock", nextBlock); 
		
		return js;  // ��ü�� ���ϵȴ�.
	}
	
	
	
	// ��� �����ϱ�
	@RequestMapping(value="/{cidx}/commentDeleteAction.aws", method=RequestMethod.GET)
	public JSONObject commentDeleteAction(
			CommentVo cv, 
			@PathVariable("cidx") int cidx, //�̰� ���ؼ� �� bidx���� ���� �� �ִ�.
			HttpServletRequest request
			) throws Exception{
		
		JSONObject js = new JSONObject();
		
		int midx = Integer.parseInt(request.getSession().getAttribute("midx").toString());
		cv.setMidx(midx);
		cv.setCidx(cidx);
		cv.setCip(userIp.getUserIp(request));
		
		int value = commentService.commentDelete(cv);
		
		js.put("value", value);
	    return js;
	}
	
	
	// ��۾��� 
	@RequestMapping(value="/commentWriteAction.aws", method=RequestMethod.POST)
	public JSONObject commentWriteAction(
			CommentVo cv,
			HttpServletRequest request
			) throws Exception{
		
		System.out.println("��� �׼� ���Ծ�?");
		System.out.println("cv" + cv.getCcontents());
		JSONObject js = new JSONObject();
		
		String cip = userIp.getUserIp(request);// getUserIp() �޼��带 ���� Ŭ���̾�Ʈ�� IP �ּҸ� ������
		cv.setCip(cip);  // cv�� IP�� ����
		
		int value = commentService.commentInsert(cv);
		js.put("value", value);
		
		return js;
	}
	
}