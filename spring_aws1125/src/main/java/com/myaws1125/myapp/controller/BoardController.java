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

}
