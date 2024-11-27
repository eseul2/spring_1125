package com.myaws1125.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/bookmark/")
public class BookmarkController {
	
	
	//로거 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 	

	
	// 북마크 리스트 화면가기 
	@RequestMapping(value= "bookmarkList.aws", method=RequestMethod.GET)
	public String bookmarkList() {
		
		String path = "WEB-INF/bookmark/bookmarkList";
		return path;
	}
}
