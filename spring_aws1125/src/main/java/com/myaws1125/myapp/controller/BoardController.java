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
	
	
	//로거 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	// BoardService 타입의 객체를 자동으로 주입. 주입 실패 시에도 에러가 발생하지 않고 null로 설정됨
	@Autowired(required = false) 
	private BoardService boardService;
	
	// PageMaker 객체를 자동으로 주입하여 페이지 정보를 설정
	@Autowired(required = false) 
	private PageMaker pm;
	
	@Resource(name="uploadPath") // 리소스는 사용할 때 name값을 서블릿.xml 빈에 등록된 id이름으로 넣어야한다. 이름이 같은 애를 찾아서 주입.
	private String uploadPath;
	
	
	
	// 글쓰기 화면 가기
	@RequestMapping(value= "boardWrite.aws", method=RequestMethod.GET)
	public String boardWrite() {
		System.out.println("보드 라이트 ");
		String path = "WEB-INF/board/boardWrite";
		return path;
	}
	
	
	// 자유게시판 글 내용 화면 가기 
	@RequestMapping(value= "boardContents.aws", method=RequestMethod.GET)
	public String boardContents() {
		
		String path = "WEB-INF/board/boardContents";
		return path;
	}
	
	
	// 글 수정 화면 가기 
	@RequestMapping(value= "boardModify.aws", method=RequestMethod.GET)
	public String boardModify() {
		
		String path = "WEB-INF/board/boardModify";
		return path;
	}
	
	
	
	//게시글 목록을 조회하고 검색 조건에 맞는 게시글을 화면에 표시하는 메서드
	@RequestMapping(value="boardList.aws", method=RequestMethod.GET)
	public String boardList(SearchCriteria scri, Model model) {
		System.out.println("보드 리스트 들어옴");
		
		// 총 게시글 수를 검색 조건(scri)을 기준으로 조회
		int cnt = boardService.boardTotalCount(scri);
		// PageMaker 객체에 검색 조건과 총 게시글 수를 설정하여 페이지 네비게이션을 준비
		pm.setScri(scri);
		pm.setTotalCount(cnt);
			
		// boardService에서 주어진 검색 조건(scri)을 기반으로 게시글 목록을 조회
		ArrayList<BoardVo> blist = boardService.boardSelectAll(scri);
		
		// 조회된 게시글 목록을 "blist"라는 이름으로 모델에 추가하여 JSP 뷰 페이지로 전달
		model.addAttribute("blist",blist);
		// PageMaker 객체도 모델에 추가하여 JSP 뷰에서 페이지 정보도 접근할 수 있도록 설정
		model.addAttribute("pm",pm);
			
		//경로에 해당하는 JSP 뷰 페이지를 반환
		return "WEB-INF/board/boardList";
		}
	
	
	// 자유게시판 글작성과 파일 업로드 처리하기
	@RequestMapping(value= "boardWriteAction.aws")
	public String boardWriteAction(
		BoardVo bv, // 게시글 정보를 담고 있는 BoardVo 객체를 매개변수로 받음
		@RequestParam("filename") MultipartFile filename, // 업로드된 파일을 받기 위한 MultipartFile 객체
		HttpServletRequest request,
		RedirectAttributes rttr
		) throws Exception { // 윗단에 보고를 하는것
		System.out.println("boardWriteAction 들어옴");
		MultipartFile file = filename; //저장된 파일 이름 꺼내기 
		String uploadedFileName=""; // 파일이 업로드된 후 저장된 파일명을 저장할 변수
		System.out.println("boardWriteAction 들어옴");
		if(! file.getOriginalFilename().equals("")) { // 해당 파일이 존재한다면
			 // 파일을 서버에 저장하고 저장된 파일 이름을 반환받음
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		}
		String midx = request.getSession().getAttribute("midx").toString();
		int midx_int = Integer.parseInt(midx);
		String ip = getUserIp(request);
		
		bv.setUploadedFilename(uploadedFileName);  // vo에 담아서 가져가기 
		bv.setMidx(midx_int);
		bv.setIp(ip);
		
		int value = boardService.boardInsert(bv);
		System.out.println("value==="+ value);

		String path="";
		if(value == 1) { //성공하면
			path = "redirect:/board/boardList.aws";
		}else {
			rttr.addFlashAttribute("msg","입력이 잘못되었습니다.");
			path = "redirect:/board/boardWrite.aws"; // 리다이랙트로 넘기는것은 내부가 아니라 외부기 때문에 전체경로를 넘겨줘야 한다(aws)
		}
		return path;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	// 서버 ip 추출하는 메소드 
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
	
