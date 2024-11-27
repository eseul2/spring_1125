package com.myaws1125.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/bookmark/")
public class BookmarkController {
	
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 	

	
	// �ϸ�ũ ����Ʈ ȭ�鰡�� 
	@RequestMapping(value= "bookmarkList.aws", method=RequestMethod.GET)
	public String bookmarkList() {
		
		String path = "WEB-INF/bookmark/bookmarkList";
		return path;
	}
}
