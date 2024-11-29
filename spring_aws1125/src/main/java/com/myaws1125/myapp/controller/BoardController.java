package com.myaws1125.myapp.controller;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.PageMaker;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.service.BoardService;

@Controller
@RequestMapping(value="/board/")
public class BoardController {
	
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	// BoardService Ÿ���� ��ü�� �ڵ����� ����. ���� ���� �ÿ��� ������ �߻����� �ʰ� null�� ������
	@Autowired(required = false) 
	private BoardService boardService;
	
	// PageMaker ��ü�� �ڵ����� �����Ͽ� ������ ������ ����
	@Autowired(required = false) 
	private PageMaker pm;
	
	
	
	// �۾��� ȭ�� ����
	@RequestMapping(value= "boardWrite.aws", method=RequestMethod.GET)
	public String boardWrite() {
		
		String path = "WEB-INF/board/boardWrite";
		return path;
	}
	
	
	// �����Խ��� �� ���� ȭ�� ���� 
	@RequestMapping(value= "boardContents.aws", method=RequestMethod.GET)
	public String boardContents() {
		
		String path = "WEB-INF/board/boardContents";
		return path;
	}
	
	
	// �� ���� ȭ�� ���� 
	@RequestMapping(value= "boardModify.aws", method=RequestMethod.GET)
	public String boardModify() {
		
		String path = "WEB-INF/board/boardModify";
		return path;
	}
	
	
	
	//�Խñ� ����� ��ȸ�ϰ� �˻� ���ǿ� �´� �Խñ��� ȭ�鿡 ǥ���ϴ� �޼���
	@RequestMapping(value="boardList.aws", method=RequestMethod.GET)
	public String boardList(SearchCriteria scri, Model model) {
		
		// �� �Խñ� ���� �˻� ����(scri)�� �������� ��ȸ
		int cnt = boardService.boardTotalCount(scri);
		// PageMaker ��ü�� �˻� ���ǰ� �� �Խñ� ���� �����Ͽ� ������ �׺���̼��� �غ�
		pm.setScri(scri);
		pm.setTotalCount(cnt);
			
		// boardService���� �־��� �˻� ����(scri)�� ������� �Խñ� ����� ��ȸ
		ArrayList<BoardVo> blist = boardService.boardSelectAll(scri);
		
		// ��ȸ�� �Խñ� ����� "blist"��� �̸����� �𵨿� �߰��Ͽ� JSP �� �������� ����
		model.addAttribute("blist",blist);
		// PageMaker ��ü�� �𵨿� �߰��Ͽ� JSP �信�� ������ ������ ������ �� �ֵ��� ����
		model.addAttribute("pm",pm);
			
		//��ο� �ش��ϴ� JSP �� �������� ��ȯ
		return "WEB-INF/board/boardList";
		}

}
	
