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
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
    @Autowired
    private BookmarkService bookmarkService;
    
	@Autowired(required = false) 
	private ReviewService reviewService;
    
    
    
	// ���ϱ� �߰�/����
	@ResponseBody
	@RequestMapping(value = "toggleBookmark", method = RequestMethod.POST)
	public String toggleBookmark(@RequestParam("review_id") int review_id, HttpSession session) {
	   
		logger.info("toggleBookmark ȣ���: review_id = {}", review_id);

	    String midxStr = (String) session.getAttribute("midx");

	    if (midxStr == null) {
	        return "loginRequired";
	    }

	    try {
	        Integer midx = Integer.parseInt(midxStr);
	        boolean isBookmarked = bookmarkService.toggleBookmark(midx, review_id);
	        return isBookmarked ? "added" : "removed";
	    } catch (NumberFormatException e) {
	        logger.error("���� �� ��ȯ ����", e);
	        return "loginRequired";
	    }
	}

    
    
    
    // �� �Խ��� ��ȸ
    @RequestMapping(value = "bookmarkList.aws", method = RequestMethod.GET)
    public String bookmarkList(HttpSession session, Model model) {
     
        // ���ǿ��� String �� ��������
        String midxStr = (String) session.getAttribute("midx");

        // �α��� ���� Ȯ��
        if (midxStr == null) {
            return "redirect:/member/memberLogin.aws"; // �α������� ������ �α��� �������� ���𷺼�
        }

        try {
            // String�� Integer�� ��ȯ
            Integer midx = Integer.parseInt(midxStr);
            
            // ���� ���� ��� ��ȸ
            ArrayList<ReviewVo> bookmarkedReviews = bookmarkService.getBookmarkedReviews(midx);
            model.addAttribute("bookmarkedReviews", bookmarkedReviews);

            return "WEB-INF/bookmark/bookmarkList"; // �� ��� ȭ�� JSP ��ȯ
        } catch (NumberFormatException e) {
            // ���� ���� �߸��� ��� ó��
            return "redirect:/member/memberLogin.aws"; // �߸��� ���� ���� ������ �α��� �������� ���𷺼�
        }
    }
    
    
    
    
    
    
	// �ϸ�ũ ���� ������Ʈ
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
	 * if (memberId == null) { // �α������� ���� ��� response.put("success", false);
	 * response.put("message", "�α����� �ʿ��մϴ�."); return response; }
	 * 
	 * int reviewId = Integer.parseInt(data.get("reviewId")); // JSON�� Ű�κ��� reviewId
	 * ���� String bookmark = data.get("bookmark"); // JSON�� Ű�κ��� bookmark ���� ����
	 * 
	 * // ���� ȣ���ؼ� �ϸ�ũ ���� ������Ʈ boolean success =
	 * reviewService.updateBookmark(memberId, reviewId, bookmark);
	 * 
	 * 
	 * response.put("success", success); response.put("redirectUrl",
	 * "/review/bookmarkList.aws"); // �����̷�Ʈ URL �߰� return response; }
	 */	
    
	
	
	

}
