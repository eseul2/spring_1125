package com.myaws1125.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/review/")
public class ReviewController {
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	// ����ã�� ��� ȭ��
	@RequestMapping(value= "reviewList.aws", method=RequestMethod.GET)
	public String reviewList() {
			
		String path = "WEB-INF/review/reviewList";
		return path;
	}

}
