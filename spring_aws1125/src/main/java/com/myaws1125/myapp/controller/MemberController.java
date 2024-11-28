package com.myaws1125.myapp.controller;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws1125.myapp.domain.MemberVo;
import com.myaws1125.myapp.service.MemberService;

@Controller
@RequestMapping(value="/member/")
public class MemberController {
	
	//로거 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	@Autowired /* 객체 생셩시켰으니 주입시킨다. 업캐스팅 */
	private MemberService memberService;
	
	/* 비밀번호를 인코드해서 암호화 하는 작업 */
	@Autowired(required=false)
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	
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
	
	
	@RequestMapping(value="memberJoinAction.aws",method=RequestMethod.POST)
	public String memberJoinAction(MemberVo mv) {
		
		// 넘어온 비밀번호를 비밀번호 암호화 시키기
		String memberpw_enc = bCryptPasswordEncoder.encode(mv.getMemberpw());
		// 암호화 시킨 비밀번호를 다시 넘기기
		mv.setMemberpw(memberpw_enc);
		
		int value = memberService.memberInsert(mv);
		logger.info("value: " + value);  
		
		String path="";
		if (value == 1) { /* 성공하면 */
			path ="redirect:/member/main.aws";
		} else if (value == 0) { /* 실패하면 */
			path ="redirect:/member/memberJoin.aws";
		}
		return path;   
 	}
	
	//사용자가 입력한 아이디의 중복 여부를 확인하는 기능
	@ResponseBody
	@RequestMapping(value="memberIdCheck.aws", method=RequestMethod.POST)
	public JSONObject memberIdCheck(@RequestParam("memberId") String memberId) {
		
		 // 입력된 아이디를 확인하여 중복된 아이디가 있는지 검사
		int cnt = memberService.memberIdCheck(memberId);
		
		// JSON 객체 생성
		JSONObject obj = new JSONObject();
		// 중복된 아이디 개수를 "cnt"라는 키로 JSON 객체에 추가
		obj.put("cnt", cnt);
		
		 // JSON 객체를 반환하여 클라이언트로 전달
		return obj;
	}	
	
	
	

	
	
	
	
	


}
