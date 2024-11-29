package com.myaws1125.myapp.controller;


import java.net.InetAddress;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.type.BigIntegerTypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.PageMaker;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.service.BoardService;
import com.myaws1125.myapp.util.UploadFileUtiles;

@Controller
@RequestMapping(value="/board/")
public class BoardController {
	
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	// BoardService Ÿ���� ��ü�� �ڵ����� ����. ���� ���� �ÿ��� ������ �߻����� �ʰ� null�� ������
	@Autowired(required = false) 
	private BoardService boardService;
	
	// PageMaker ��ü�� �ڵ����� �����Ͽ� ������ ������ ����
	@Autowired(required = false) 
	private PageMaker pm;
	
	@Resource(name="uploadPath") // ���ҽ��� ����� �� name���� ����.xml �� ��ϵ� id�̸����� �־���Ѵ�. �̸��� ���� �ָ� ã�Ƽ� ����.
	private String uploadPath;
	
	
	
	// �۾��� ȭ�� ����
	@RequestMapping(value= "boardWrite.aws", method=RequestMethod.GET)
	public String boardWrite() {
		System.out.println("���� ����Ʈ ");
		String path = "WEB-INF/board/boardWrite";
		return path;
	}
	
	
	// �����Խ��� �� ���� ȭ�� ���� 
	@RequestMapping(value= "boardContents.aws", method=RequestMethod.GET)
	public String boardContents() {
		
		String path = "WEB-INF/board/boardContents";
		return path;
	}
	
	
	// �� ���� ȭ�� ���� 
	@RequestMapping(value= "boardModify.aws", method=RequestMethod.GET)
	public String boardModify() {
		
		String path = "WEB-INF/board/boardModify";
		return path;
	}
	
	
	
	//�Խñ� ����� ��ȸ�ϰ� �˻� ���ǿ� �´� �Խñ��� ȭ�鿡 ǥ���ϴ� �޼���
	@RequestMapping(value="boardList.aws", method=RequestMethod.GET)
	public String boardList(SearchCriteria scri, Model model) {
		System.out.println("���� ����Ʈ ����");
		
		// �� �Խñ� ���� �˻� ����(scri)�� �������� ��ȸ
		int cnt = boardService.boardTotalCount(scri);
		// PageMaker ��ü�� �˻� ���ǰ� �� �Խñ� ���� �����Ͽ� ������ �׺���̼��� �غ�
		pm.setScri(scri);
		pm.setTotalCount(cnt);
			
		// boardService���� �־��� �˻� ����(scri)�� ������� �Խñ� ����� ��ȸ
		ArrayList<BoardVo> blist = boardService.boardSelectAll(scri);
		
		// ��ȸ�� �Խñ� ����� "blist"��� �̸����� �𵨿� �߰��Ͽ� JSP �� �������� ����
		model.addAttribute("blist",blist);
		// PageMaker ��ü�� �𵨿� �߰��Ͽ� JSP �信�� ������ ������ ������ �� �ֵ��� ����
		model.addAttribute("pm",pm);
			
		//��ο� �ش��ϴ� JSP �� �������� ��ȯ
		return "WEB-INF/board/boardList";
		}
	
	
	// �����Խ��� ���ۼ��� ���� ���ε� ó���ϱ�
	@RequestMapping(value= "boardWriteAction.aws")
	public String boardWriteAction(
		BoardVo bv, // �Խñ� ������ ��� �ִ� BoardVo ��ü�� �Ű������� ����
		@RequestParam("filename") MultipartFile filename, // ���ε�� ������ �ޱ� ���� MultipartFile ��ü
		HttpServletRequest request,
		RedirectAttributes rttr
		) throws Exception { // ���ܿ� ���� �ϴ°�
		System.out.println("boardWriteAction ����");
		MultipartFile file = filename; //����� ���� �̸� ������ 
		String uploadedFileName=""; // ������ ���ε�� �� ����� ���ϸ��� ������ ����
		System.out.println("boardWriteAction ����");
		if(! file.getOriginalFilename().equals("")) { // �ش� ������ �����Ѵٸ�
			 // ������ ������ �����ϰ� ����� ���� �̸��� ��ȯ����
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		}
		String midx = request.getSession().getAttribute("midx").toString();
		int midx_int = Integer.parseInt(midx);
		String ip = getUserIp(request);
		
		bv.setUploadedFilename(uploadedFileName);  // vo�� ��Ƽ� �������� 
		bv.setMidx(midx_int);
		bv.setIp(ip);
		
		int value = boardService.boardInsert(bv);
		System.out.println("value==="+ value);

		String path="";
		if(value == 1) { //�����ϸ�
			path = "redirect:/board/boardList.aws";
		}else {
			rttr.addFlashAttribute("msg","�Է��� �߸��Ǿ����ϴ�.");
			path = "redirect:/board/boardWrite.aws"; // �����̷�Ʈ�� �ѱ�°��� ���ΰ� �ƴ϶� �ܺα� ������ ��ü��θ� �Ѱ���� �Ѵ�(aws)
		}
		return path;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	// ���� ip �����ϴ� �޼ҵ� 
	public String getUserIp(HttpServletRequest request) throws Exception {
				
		String ip = null;
		//  HttpServletRequest request = 
		// ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		ip = request.getHeader("X-Forwarded-For");
		        
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("Proxy-Client-IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("HTTP_CLIENT_IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("X-Real-IP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("X-RealIP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("REMOTE_ADDR");
		}	
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getRemoteAddr(); 
		}
		        
		        
		if(ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
			InetAddress address = InetAddress.getLocalHost();
			ip = address.getHostAddress();
		}
		return ip;
	}

}
	
