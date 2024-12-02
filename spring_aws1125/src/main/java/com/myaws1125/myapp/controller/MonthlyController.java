package com.myaws1125.myapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws1125.myapp.domain.MonthlyVo;
import com.myaws1125.myapp.domain.PageMaker;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.service.MonthlyService;

@Controller
@RequestMapping(value="/monthly/")
public class MonthlyController {
	
	//로거 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	// MonthlyService 타입의 객체를 자동으로 주입. 주입 실패 시에도 에러가 발생하지 않고 null로 설정됨
	@Autowired(required = false) 
	private MonthlyService monthlyService;
	
	// PageMaker 객체를 자동으로 주입하여 페이지 정보를 설정
	@Autowired(required = false) 
	private PageMaker pm;
	
	
	
	
	
	//게시글 목록을 조회하고 검색 조건에 맞는 게시글을 화면에 표시하는 메서드
	@RequestMapping(value="monthlyList.aws", method=RequestMethod.GET)
	public String monthlyList(SearchCriteria scri, Model model, HttpSession session) {
		
	    // 세션에서 사용자 등급 가져오기
	    String grade = (String) session.getAttribute("grade");
		
		// 총 게시글 수를 검색 조건(scri)을 기준으로 조회
		int cnt = monthlyService.monthlyTotalCount(scri);
		// PageMaker 객체에 검색 조건과 총 게시글 수를 설정하여 페이지 네비게이션을 준비
		pm.setScri(scri);
		pm.setTotalCount(cnt);
			
		// boardService에서 주어진 검색 조건(scri)을 기반으로 게시글 목록을 조회
		ArrayList<MonthlyVo> mlist = monthlyService.monthlySelectAll(scri);
		
		// 조회된 게시글 목록을 "mlist"라는 이름으로 모델에 추가하여 JSP 뷰 페이지로 전달
		model.addAttribute("mlist",mlist);
		// PageMaker 객체도 모델에 추가하여 JSP 뷰에서 페이지 정보도 접근할 수 있도록 설정
		model.addAttribute("pm",pm);
	    // 사용자 등급을 모델에 추가하여 뷰에서 활용할 수 있도록 설정
	    model.addAttribute("grade", grade);
			
		//경로에 해당하는 JSP 뷰 페이지를 반환
		return "WEB-INF/monthly/monthlyList";
		}
	
	
	// 글쓰기 화면 
	@RequestMapping(value= "monthlyWrite.aws", method=RequestMethod.GET)
	public String monthlyWrite() {
			
		String path = "WEB-INF/monthly/monthlyWrite";
		return path;
	}
	
	
	
	// 글쓰기 처리 
	@RequestMapping(value= "monthlyWriteAction.aws")
    public String monthlyWriteAction(
            MonthlyVo monv, // 게시글 정보를 담고 있는 객체
            HttpServletRequest request,
            RedirectAttributes rttr) {

        System.out.println("monthlyWriteAction 들어옴");

        // 사용자 정보 설정
        String midx = request.getSession().getAttribute("midx").toString();
        int midx_int = Integer.parseInt(midx);

        // BoardVo 객체에 데이터 설정
        monv.setMidx(midx_int);

        // 게시글 삽입 서비스 호출
        int value = monthlyService.monthlyInsert(monv);
        System.out.println("value===" + value);

        // 리다이렉트 경로 설정
        String path = "";
        if (value == 1) { // 성공 시
            path = "redirect:/monthly/monthlyList.aws";
        } else {
            rttr.addFlashAttribute("msg", "입력이 잘못되었습니다.");
            path = "redirect:/monthly/monthlyWrite.aws";
        }
        return path;
    }

		

}
