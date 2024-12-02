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
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	// MonthlyService Ÿ���� ��ü�� �ڵ����� ����. ���� ���� �ÿ��� ������ �߻����� �ʰ� null�� ������
	@Autowired(required = false) 
	private MonthlyService monthlyService;
	
	// PageMaker ��ü�� �ڵ����� �����Ͽ� ������ ������ ����
	@Autowired(required = false) 
	private PageMaker pm;
	
	
	
	
	
	//�Խñ� ����� ��ȸ�ϰ� �˻� ���ǿ� �´� �Խñ��� ȭ�鿡 ǥ���ϴ� �޼���
	@RequestMapping(value="monthlyList.aws", method=RequestMethod.GET)
	public String monthlyList(SearchCriteria scri, Model model, HttpSession session) {
		
	    // ���ǿ��� ����� ��� ��������
	    String grade = (String) session.getAttribute("grade");
		
		// �� �Խñ� ���� �˻� ����(scri)�� �������� ��ȸ
		int cnt = monthlyService.monthlyTotalCount(scri);
		// PageMaker ��ü�� �˻� ���ǰ� �� �Խñ� ���� �����Ͽ� ������ �׺���̼��� �غ�
		pm.setScri(scri);
		pm.setTotalCount(cnt);
			
		// boardService���� �־��� �˻� ����(scri)�� ������� �Խñ� ����� ��ȸ
		ArrayList<MonthlyVo> mlist = monthlyService.monthlySelectAll(scri);
		
		// ��ȸ�� �Խñ� ����� "mlist"��� �̸����� �𵨿� �߰��Ͽ� JSP �� �������� ����
		model.addAttribute("mlist",mlist);
		// PageMaker ��ü�� �𵨿� �߰��Ͽ� JSP �信�� ������ ������ ������ �� �ֵ��� ����
		model.addAttribute("pm",pm);
	    // ����� ����� �𵨿� �߰��Ͽ� �信�� Ȱ���� �� �ֵ��� ����
	    model.addAttribute("grade", grade);
			
		//��ο� �ش��ϴ� JSP �� �������� ��ȯ
		return "WEB-INF/monthly/monthlyList";
		}
	
	
	// �۾��� ȭ�� 
	@RequestMapping(value= "monthlyWrite.aws", method=RequestMethod.GET)
	public String monthlyWrite() {
			
		String path = "WEB-INF/monthly/monthlyWrite";
		return path;
	}
	
	
	
	// �۾��� ó�� 
	@RequestMapping(value= "monthlyWriteAction.aws")
    public String monthlyWriteAction(
            MonthlyVo monv, // �Խñ� ������ ��� �ִ� ��ü
            HttpServletRequest request,
            RedirectAttributes rttr) {

        System.out.println("monthlyWriteAction ����");

        // ����� ���� ����
        String midx = request.getSession().getAttribute("midx").toString();
        int midx_int = Integer.parseInt(midx);

        // BoardVo ��ü�� ������ ����
        monv.setMidx(midx_int);

        // �Խñ� ���� ���� ȣ��
        int value = monthlyService.monthlyInsert(monv);
        System.out.println("value===" + value);

        // �����̷�Ʈ ��� ����
        String path = "";
        if (value == 1) { // ���� ��
            path = "redirect:/monthly/monthlyList.aws";
        } else {
            rttr.addFlashAttribute("msg", "�Է��� �߸��Ǿ����ϴ�.");
            path = "redirect:/monthly/monthlyWrite.aws";
        }
        return path;
    }

		

}
