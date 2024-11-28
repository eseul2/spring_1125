package com.myaws1125.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/monthly/")
public class MonthlyController {
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	// �̴��� ���� ��� ȭ�� 
	@RequestMapping(value= "monthlyList.aws", method=RequestMethod.GET)
	public String monthlyList() {
			
		String path = "WEB-INF/monthly/monthlyList";
		return path;
	}
	
	// �۾��� ȭ�� 
	@RequestMapping(value= "monthlyWrite.aws", method=RequestMethod.GET)
	public String monthlyWrite() {
			
		String path = "WEB-INF/monthly/monthlyWrite";
		return path;
	}
		

}
