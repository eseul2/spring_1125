package com.myaws1125.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.service.BookmarkService;
import com.myaws1125.myapp.service.ReviewService;

@Controller
@RequestMapping("/bookmark/")
public class BookmarkController {
	
	//로거 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
    @Autowired
    private BookmarkService bookmarkService;
    
	@Autowired(required = false) 
	private ReviewService reviewService;
    
    
    
	// 찜하기 추가/삭제
	@ResponseBody
	@RequestMapping(value = "toggleBookmark", method = RequestMethod.POST)
	public String toggleBookmark(@RequestParam("review_id") int review_id, HttpSession session) {
	   
		logger.info("toggleBookmark 호출됨: review_id = {}", review_id);

	    String midxStr = (String) session.getAttribute("midx");

	    if (midxStr == null) {
	        return "loginRequired";
	    }

	    try {
	        Integer midx = Integer.parseInt(midxStr);
	        boolean isBookmarked = bookmarkService.toggleBookmark(midx, review_id);
	        return isBookmarked ? "added" : "removed";
	    } catch (NumberFormatException e) {
	        logger.error("세션 값 변환 오류", e);
	        return "loginRequired";
	    }
	}

    
    
    
    // 찜 게시판 조회
    @RequestMapping(value = "bookmarkList.aws", method = RequestMethod.GET)
    public String bookmarkList(HttpSession session, Model model) {
     
        // 세션에서 String 값 가져오기
        String midxStr = (String) session.getAttribute("midx");

        // 로그인 여부 확인
        if (midxStr == null) {
            return "redirect:/member/memberLogin.aws"; // 로그인하지 않으면 로그인 페이지로 리디렉션
        }

        try {
            // String을 Integer로 변환
            Integer midx = Integer.parseInt(midxStr);
            
            // 찜한 리뷰 목록 조회
            ArrayList<ReviewVo> bookmarkedReviews = bookmarkService.getBookmarkedReviews(midx);
            model.addAttribute("bookmarkedReviews", bookmarkedReviews);

            return "WEB-INF/bookmark/bookmarkList"; // 찜 목록 화면 JSP 반환
        } catch (NumberFormatException e) {
            // 세션 값이 잘못된 경우 처리
            return "redirect:/member/memberLogin.aws"; // 잘못된 세션 값이 있으면 로그인 페이지로 리디렉션
        }
    }
    
    
    
    
    
    
	// 북마크 상태 업데이트
	/*
	 * @PostMapping("/updateBookmark")
	 * 
	 * @ResponseBody public Map<String, Object> updateBookmark(@RequestBody
	 * Map<String, String> data, HttpSession session) {
	 * 
	 * 
	 * Integer memberId = (Integer) session.getAttribute("midx"); Map<String,
	 * Object> response = new HashMap<>();
	 * 
	 * if (memberId == null) { // 로그인하지 않은 경우 response.put("success", false);
	 * response.put("message", "로그인이 필요합니다."); return response; }
	 * 
	 * int reviewId = Integer.parseInt(data.get("reviewId")); // JSON의 키로부터 reviewId
	 * 추출 String bookmark = data.get("bookmark"); // JSON의 키로부터 bookmark 상태 추출
	 * 
	 * // 서비스 호출해서 북마크 상태 업데이트 boolean success =
	 * reviewService.updateBookmark(memberId, reviewId, bookmark);
	 * 
	 * 
	 * response.put("success", success); response.put("redirectUrl",
	 * "/review/bookmarkList.aws"); // 리다이렉트 URL 추가 return response; }
	 */	
    
	
	
	

}
