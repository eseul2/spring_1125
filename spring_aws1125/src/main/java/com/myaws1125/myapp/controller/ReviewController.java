package com.myaws1125.myapp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws1125.myapp.domain.MonthlyVo;
import com.myaws1125.myapp.domain.PageMaker;
import com.myaws1125.myapp.domain.ReviewVo;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.service.ReviewService;
import com.myaws1125.myapp.util.MediaUtils;
import com.myaws1125.myapp.util.UploadFileUtiles;

@Controller
@RequestMapping(value="/review/")
public class ReviewController {
	
	//로거 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	// ReviewService 타입의 객체를 자동으로 주입. 주입 실패 시에도 에러가 발생하지 않고 null로 설정됨
	@Autowired(required = false) 
	private ReviewService reviewService;
	
	// PageMaker 객체를 자동으로 주입하여 페이지 정보를 설정
	@Autowired(required = false) 
	private PageMaker pm;
	
	@Resource(name="uploadPath") // 리소스는 사용할 때 name값을 서블릿.xml 빈에 등록된 id이름으로 넣어야한다. 이름이 같은 애를 찾아서 주입.
	private String uploadPath;
	
	
	
	
	// 메인페이지
	@RequestMapping(value= "main.aws", method=RequestMethod.GET)
	public String main(SearchCriteria scri, Model model) {
		
		// 총 게시글 수를 검색 조건(scri)을 기준으로 조회
		int cnt = reviewService.reviewTotalCount(scri);
		// PageMaker 객체에 검색 조건과 총 게시글 수를 설정하여 페이지 네비게이션을 준비
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		// reviewService에서 주어진 검색 조건(scri)을 기반으로 게시글 목록을 조회
		ArrayList<ReviewVo> rlist = reviewService.reviewSelectAll(scri);
		
		// 조회된 게시글 목록을 "rlist"라는 이름으로 모델에 추가하여 JSP 뷰 페이지로 전달
		model.addAttribute("rlist",rlist);
		// PageMaker 객체도 모델에 추가하여 JSP 뷰에서 페이지 정보도 접근할 수 있도록 설정
		model.addAttribute("pm",pm);
		
		String path = "WEB-INF/review/main";
		return path;
	}
	
	

	
	//빵집 화면 게시글 목록을 조회하고 검색 조건에 맞는 게시글을 화면에 표시하는 메서드
	@RequestMapping(value="reviewList.aws", method=RequestMethod.GET)
	public String reviewList(SearchCriteria scri, Model model, HttpSession session) {
		
	    // 세션에서 사용자 등급 가져오기
	    String grade = (String) session.getAttribute("grade");
	    
	    // area 파라미터 처리: URL로부터 전달받은 area 값을 SearchCriteria에 설정
	    String area = scri.getArea();  // SearchCriteria에서 area를 가져옴
	    if (area != null && !area.isEmpty()) {
	        scri.setArea(area);  // 지역 정보가 있으면 SearchCriteria에 설정
	    }
	    
	  
		// 총 게시글 수를 검색 조건(scri)을 기준으로 조회
		int cnt = reviewService.reviewTotalCount(scri);
		// PageMaker 객체에 검색 조건과 총 게시글 수를 설정하여 페이지 네비게이션을 준비
		pm.setScri(scri);
		pm.setTotalCount(cnt);
			
		// reviewService에서 주어진 검색 조건(scri)을 기반으로 게시글 목록을 조회
		ArrayList<ReviewVo> rlist = reviewService.reviewSelectAll(scri);
		
		// 조회된 게시글 목록을 "rlist"라는 이름으로 모델에 추가하여 JSP 뷰 페이지로 전달
		model.addAttribute("rlist",rlist);
		// PageMaker 객체도 모델에 추가하여 JSP 뷰에서 페이지 정보도 접근할 수 있도록 설정
		model.addAttribute("pm",pm);
	    // 사용자 등급을 모델에 추가하여 뷰에서 활용할 수 있도록 설정
	    model.addAttribute("grade", grade);
			
		//경로에 해당하는 JSP 뷰 페이지를 반환
		return "WEB-INF/review/reviewList";
		}
	
	
	
	// 빵집찾기 글쓰기 화면
	@RequestMapping(value= "reviewWrite.aws", method=RequestMethod.GET)
	public String reviewWrite() {
			
		String path = "WEB-INF/review/reviewWrite";
		return path;
	}
	
	// 글쓰기 넘기기
	@RequestMapping(value = "reviewWriteAction.aws")
	public String reviewWriteAction(
	        ReviewVo rv,
	        @RequestParam("attachfile") MultipartFile[] attachfiles,
	        HttpServletRequest request,
	        RedirectAttributes rttr
	) throws Exception {
	    String uploadPath = "D:/dev/bakeryUpload"; // 업로드 경로
	    List<MultipartFile> fileList = Arrays.asList(attachfiles);
	    List<String> uploadedFileNames = UploadFileUtiles.uploadFiles(uploadPath, fileList);

	    // 파일 이름 리스트를 문자열로 변환 (예: "file1.jpg,file2.png")
	    String uploadedFileNamesStr = String.join(",", uploadedFileNames);

	    // 사용자 정보 설정
	    String midx = request.getSession().getAttribute("midx").toString();
	    int midx_int = Integer.parseInt(midx);

	    // ReviewVo에 데이터 설정
	    rv.setMidx(midx_int);
	    rv.setFilename(uploadedFileNamesStr); // 변환된 파일 이름 문자열 설정

	    // 게시글 삽입 서비스 호출
	    int value = reviewService.reviewInsert(rv);

	    String path;
	    if (value == 1) {
	        path = "redirect:/review/reviewList.aws";
	    } else {
	        rttr.addFlashAttribute("msg", "입력이 잘못되었습니다.");
	        path = "redirect:/review/reviewWrite.aws";
	    }
	    return path;
	}
	
	
	
	

	  // 글 내용 보여주기 기능	  
	 @RequestMapping(value= "reviewContents.aws") 
	 public String reviewContents(
			 @RequestParam("review_id") int review_id, 
			 Model model,
			 HttpSession session // 회원등급 가져오기
			 ) {
		 
		 // 세션에서 사용자 등급 가져오기
		 String grade = (String) session.getAttribute("grade");
	  
		 ReviewVo rv = reviewService.reviewSelectOne(review_id);
	 
	    // ReviewVo에서 파일 목록을 가져와서 모델에 추가
	    List<String> fileNames = rv.getFileNames();
	    model.addAttribute("rv", rv);
	    model.addAttribute("fileNames", fileNames);
	    model.addAttribute("grade", grade);
	    
	 return "WEB-INF/review/reviewContents"; 
	 
	 }
	 
		// 삭제하기 기능
		@RequestMapping(value = "reviewDelete.aws", method = RequestMethod.GET)
		public String reviewDelete(
				@RequestParam("review_id") int review_id){
			
		    // 게시글 삭제 서비스 호출
		    int result = reviewService.reviewDelete(review_id);
		   
		    // 삭제 후 목록 페이지로 리다이렉트
		    return "redirect:/review/reviewList.aws";
		}	 
	 
	
		// 수정하기 화면
		@RequestMapping(value= "reviewModify.aws", method=RequestMethod.GET)
		public String reviewModify(@RequestParam("review_id") int review_id,Model model) {
				
			ReviewVo rv = reviewService.reviewSelectOne(review_id);
			model.addAttribute("rv", rv);
			
			String path = "WEB-INF/review/reviewModify";
			return path;
		}	

		
		// 수정하기 처리
		@RequestMapping(value="reviewModifyAction.aws")
		public String reviewModifyAction(
				ReviewVo rv, // 게시글 정보를 담고 있는 Vo 객체를 매개변수로 받음
		        @RequestParam("attachfile") MultipartFile[] attachfiles,
		        HttpServletRequest request,
		        RedirectAttributes rttr
				) throws Exception {
			
		    String uploadPath = "D:/dev/bakeryUpload"; // 업로드 경로
		    List<MultipartFile> fileList = Arrays.asList(attachfiles);
		    List<String> uploadedFileNames = UploadFileUtiles.uploadFiles(uploadPath, fileList);
				
		    // 파일 이름 리스트를 문자열로 변환 (예: "file1.jpg,file2.png")
		    String uploadedFileNamesStr = String.join(",", uploadedFileNames);	
			
		    rv.setFilename(uploadedFileNamesStr); // 변환된 파일 이름 문자열 설정
			
			//수정 처리
			int value = reviewService.reviewUpdate(rv); // 서비스에서 만든 메서드 호출하기
			
			String path="";
			if(value==0) {
				path = "redirect:/review/reviewModify.aws?review_id="+rv.getReview_id(); // 실패했으면 해당 키값을 다시 보여줘야 하기 때문에 bidx를 넘거야 한다
			}else {
				path = "redirect:/review/reviewContents.aws?review_id="+rv.getReview_id();
			}
			return path;
		}		
		
	
	
	
	
	@RequestMapping(value="/displayFile.aws", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(
			@RequestParam("fileName") String fileName,
			@RequestParam(value="down", defaultValue="0") int down   // 화면에 보여줄 것인가. 다운로드 하게 할 것인가?
			) {
		
		ResponseEntity<byte[]> entity = null;  //객체를 담는 애인데 byte계열을 다 담는다. 
		InputStream in = null;	// 데이터의 수로와 같은데, 처음에 읽어들이는 시작점이 InputStream
		
		try{
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1); // 확장자가 무엇인지 물어본다.
			MediaType mType = MediaUtils.getMediaType(formatName); // 위에서 확장자를 꺼내서 미디어 유틸이라는 곳에 넣는다. 왜냐면 여기서 확장자가 무엇인지 타입을 알기 위해서 (png,jpg)
			
			HttpHeaders headers = new HttpHeaders();		
			 
			in = new FileInputStream(uploadPath+fileName);  // 위에서 초기화한 애를 객체생성시켜서 해당되는 파일 위치를 
			
			if(mType != null){
				
				if (down==1) {
					fileName = fileName.substring(fileName.indexOf("_")+1);
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					headers.add("Content-Disposition", "attachment; filename=\""+
							new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");	
				}else {
					headers.setContentType(mType);	
				}
			}else{
				
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+
						new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");				
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					headers,
					HttpStatus.CREATED);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entity;
	}


		


}
