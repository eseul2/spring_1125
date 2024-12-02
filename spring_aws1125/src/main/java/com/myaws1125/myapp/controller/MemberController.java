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
	public String main() {
		
		String path = "WEB-INF/member/main";
		return path;
	}
	
	
	// 로그인 페이지 
	@RequestMapping(value= "memberLogin.aws", method=RequestMethod.GET)
	public String memberLogin() {
		//System.out.println("멤버 로그인");
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
		
		//System.out.println("멤버 로그인 액션 ");
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
	
	
	
	@RequestMapping(value="memberLoginAction.aws", method=RequestMethod.POST)
	public String memberLoginAction(
			@RequestParam("memberid") String memberid, 
			@RequestParam("memberpw") String memberpw,
			RedirectAttributes rttr, // 리다이렉트 시 일회성 데이터를 전달하기 위한 객체
			HttpSession session		// 세션 객체, 로그인 상태를 유지하는 데 사용
			) {
		//System.out.println("멤버 로그인 액션 ");
		MemberVo mv = memberService.memberLoginCheck(memberid);
		//저장된 비밀번호를 가져온다. 
		
		// 로그인 후 이동할 경로 초기화
		String path = "";
		if(mv != null) { // 회원 정보가 존재할 경우
		String reservedPw = mv.getMemberpw();  // 저장된 비밀번호를 가져옴
		
		 // 입력한 비밀번호와 저장된 비밀번호를 비교
		 if(bCryptPasswordEncoder.matches(memberpw, reservedPw)) { // 비밀번호가 일치할 경우
			 //System.out.println("비밀번호 일치");
			 rttr.addAttribute("midx",mv.getMidx());
			 rttr.addAttribute("memberId",mv.getMemberid());
			 rttr.addAttribute("memberName",mv.getMembername());
			 
			// 로그인 요청 이전 페이지가 세션에 저장되어 있는지 확인
			// logger.info("saveUrl==> " + session.getAttribute("saveUrl"));
			 
			 if(session.getAttribute("saveUrl") != null) {
				 // 이전 페이지로 리다이렉트
				 path ="redirect:"+ session.getAttribute("saveUrl").toString();
			 }else {
				// 기본 페이지로 리다이렉트
				 path ="redirect:/";
			 }
			 
		  }else {
			  // 비밀번호가 일치하지 않을 경우 경고 메시지를 설정하고 로그인 페이지로 리다이렉트
			  rttr.addFlashAttribute("msg","아이디/비밀번호를 확인해주세요");
			  path = "redirect:/member/memberLogin.aws";
		  }
		}else {
			  // 아이디에 해당하는 회원 정보가 없을 경우 경고 메시지 설정 후 로그인 페이지로 리다이렉트
			  rttr.addFlashAttribute("msg","해당하는 아이디가 없습니다."); //일회성 메소드 메세지가 한 번만 나온다
			path = "redirect:/member/memberLogin.aws";
		}
		//회원정보를 세션에 담는다.
		return path;
	}	
	
	
	// 로그아웃 기능. 
	@RequestMapping(value="memberLogout.aws", method=RequestMethod.GET)
	public String memberLogout(HttpSession session) {

		session.removeAttribute("midx");
		session.removeAttribute("memberName"); // 다 지운다.
		session.removeAttribute("memberId");
		session.invalidate(); // 세션에 저장된 모든 속성(attribute)이 삭제.
		
		// 회원 목록을 보여줄 뷰 페이지 경로 반환
		return "redirect:/";
	}
	
	

	
	
	
	
	


}
