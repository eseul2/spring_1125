package com.myaws1125.myapp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/board/")
public class BoardController {
	
	
	//로거 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	
	// 자유게시판 화면가기 
	@RequestMapping(value= "boardList.aws", method=RequestMethod.GET)
	public String boardList() {
		
		String path = "WEB-INF/board/boardList";
		return path;
	}
	
	// 글쓰기 화면 가기
	@RequestMapping(value= "boardWrite.aws", method=RequestMethod.GET)
	public String boardWrite() {
		
		String path = "WEB-INF/board/boardWrite";
		return path;
	}
	
	
	// 자유게시판 글 내용 화면 가기 
	@RequestMapping(value= "boardContents.aws", method=RequestMethod.GET)
	public String boardContents() {
		
		String path = "WEB-INF/board/boardContents";
		return path;
	}
	
	
	// 글 수정 화면 가기 
	@RequestMapping(value= "boardModify.aws", method=RequestMethod.GET)
	public String boardModify() {
		
		String path = "WEB-INF/board/boardModify";
		return path;
	}

}
