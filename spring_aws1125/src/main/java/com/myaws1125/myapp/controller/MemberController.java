package com.myaws1125.myapp.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/member/")
public class MemberController {
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	// ����������
	@RequestMapping(value= "main.aws", method=RequestMethod.GET)
	public String test() {
		
		String path = "WEB-INF/member/main";
		return path;
	}
	
	
	// �α��� ������ 
	@RequestMapping(value= "memberLogin.aws", method=RequestMethod.GET)
	public String memberLogin() {
		
		String path = "WEB-INF/member/memberLogin";
		return path; 
	}
	
	
	// ȸ������ ������
	@RequestMapping(value= "memberJoin.aws", method=RequestMethod.GET)
	public String memberJoin() {
		
		String path = "WEB-INF/member/memberJoin";
		return path; 
	}


}
