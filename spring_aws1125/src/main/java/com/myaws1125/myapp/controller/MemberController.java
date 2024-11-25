package com.myaws1125.myapp.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/member/")
public class MemberController {
	
	//로거 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	// 메인페이지
	@RequestMapping(value= "main.aws", method=RequestMethod.GET)
	public String test() {
		
		String path = "WEB-INF/member/main";
		return path;
	}
	
	
	// 로그인 페이지 
	@RequestMapping(value= "memberLogin.aws", method=RequestMethod.GET)
	public String memberLogin() {
		
		String path = "WEB-INF/member/memberLogin";
		return path; 
	}
	
	
	// 회원가입 페이지
	@RequestMapping(value= "memberJoin.aws", method=RequestMethod.GET)
	public String memberJoin() {
		
		String path = "WEB-INF/member/memberJoin";
		return path; 
	}


}
