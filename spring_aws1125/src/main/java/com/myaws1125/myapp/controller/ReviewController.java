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
	
	//�ΰ� 
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class); 
	
	
	// ReviewService Ÿ���� ��ü�� �ڵ����� ����. ���� ���� �ÿ��� ������ �߻����� �ʰ� null�� ������
	@Autowired(required = false) 
	private ReviewService reviewService;
	
	// PageMaker ��ü�� �ڵ����� �����Ͽ� ������ ������ ����
	@Autowired(required = false) 
	private PageMaker pm;
	
	@Resource(name="uploadPath") // ���ҽ��� ����� �� name���� ����.xml �� ��ϵ� id�̸����� �־���Ѵ�. �̸��� ���� �ָ� ã�Ƽ� ����.
	private String uploadPath;
	
	
	
	
	// ����������
	@RequestMapping(value= "main.aws", method=RequestMethod.GET)
	public String main(SearchCriteria scri, Model model) {
		
		// �� �Խñ� ���� �˻� ����(scri)�� �������� ��ȸ
		int cnt = reviewService.reviewTotalCount(scri);
		// PageMaker ��ü�� �˻� ���ǰ� �� �Խñ� ���� �����Ͽ� ������ �׺���̼��� �غ�
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		// reviewService���� �־��� �˻� ����(scri)�� ������� �Խñ� ����� ��ȸ
		ArrayList<ReviewVo> rlist = reviewService.reviewSelectAll(scri);
		
		// ��ȸ�� �Խñ� ����� "rlist"��� �̸����� �𵨿� �߰��Ͽ� JSP �� �������� ����
		model.addAttribute("rlist",rlist);
		// PageMaker ��ü�� �𵨿� �߰��Ͽ� JSP �信�� ������ ������ ������ �� �ֵ��� ����
		model.addAttribute("pm",pm);
		
		String path = "WEB-INF/review/main";
		return path;
	}
	
	

	
	//���� ȭ�� �Խñ� ����� ��ȸ�ϰ� �˻� ���ǿ� �´� �Խñ��� ȭ�鿡 ǥ���ϴ� �޼���
	@RequestMapping(value="reviewList.aws", method=RequestMethod.GET)
	public String reviewList(SearchCriteria scri, Model model, HttpSession session) {
		
	    // ���ǿ��� ����� ��� ��������
	    String grade = (String) session.getAttribute("grade");
	    
	    // area �Ķ���� ó��: URL�κ��� ���޹��� area ���� SearchCriteria�� ����
	    String area = scri.getArea();  // SearchCriteria���� area�� ������
	    if (area != null && !area.isEmpty()) {
	        scri.setArea(area);  // ���� ������ ������ SearchCriteria�� ����
	    }
	    
	  
		// �� �Խñ� ���� �˻� ����(scri)�� �������� ��ȸ
		int cnt = reviewService.reviewTotalCount(scri);
		// PageMaker ��ü�� �˻� ���ǰ� �� �Խñ� ���� �����Ͽ� ������ �׺���̼��� �غ�
		pm.setScri(scri);
		pm.setTotalCount(cnt);
			
		// reviewService���� �־��� �˻� ����(scri)�� ������� �Խñ� ����� ��ȸ
		ArrayList<ReviewVo> rlist = reviewService.reviewSelectAll(scri);
		
		// ��ȸ�� �Խñ� ����� "rlist"��� �̸����� �𵨿� �߰��Ͽ� JSP �� �������� ����
		model.addAttribute("rlist",rlist);
		// PageMaker ��ü�� �𵨿� �߰��Ͽ� JSP �信�� ������ ������ ������ �� �ֵ��� ����
		model.addAttribute("pm",pm);
	    // ����� ����� �𵨿� �߰��Ͽ� �信�� Ȱ���� �� �ֵ��� ����
	    model.addAttribute("grade", grade);
			
		//��ο� �ش��ϴ� JSP �� �������� ��ȯ
		return "WEB-INF/review/reviewList";
		}
	
	
	
	// ����ã�� �۾��� ȭ��
	@RequestMapping(value= "reviewWrite.aws", method=RequestMethod.GET)
	public String reviewWrite() {
			
		String path = "WEB-INF/review/reviewWrite";
		return path;
	}
	
	// �۾��� �ѱ��
	@RequestMapping(value = "reviewWriteAction.aws")
	public String reviewWriteAction(
	        ReviewVo rv,
	        @RequestParam("attachfile") MultipartFile[] attachfiles,
	        HttpServletRequest request,
	        RedirectAttributes rttr
	) throws Exception {
	    String uploadPath = "D:/dev/bakeryUpload"; // ���ε� ���
	    List<MultipartFile> fileList = Arrays.asList(attachfiles);
	    List<String> uploadedFileNames = UploadFileUtiles.uploadFiles(uploadPath, fileList);

	    // ���� �̸� ����Ʈ�� ���ڿ��� ��ȯ (��: "file1.jpg,file2.png")
	    String uploadedFileNamesStr = String.join(",", uploadedFileNames);

	    // ����� ���� ����
	    String midx = request.getSession().getAttribute("midx").toString();
	    int midx_int = Integer.parseInt(midx);

	    // ReviewVo�� ������ ����
	    rv.setMidx(midx_int);
	    rv.setFilename(uploadedFileNamesStr); // ��ȯ�� ���� �̸� ���ڿ� ����

	    // �Խñ� ���� ���� ȣ��
	    int value = reviewService.reviewInsert(rv);

	    String path;
	    if (value == 1) {
	        path = "redirect:/review/reviewList.aws";
	    } else {
	        rttr.addFlashAttribute("msg", "�Է��� �߸��Ǿ����ϴ�.");
	        path = "redirect:/review/reviewWrite.aws";
	    }
	    return path;
	}
	
	
	
	

	  // �� ���� �����ֱ� ���	  
	 @RequestMapping(value= "reviewContents.aws") 
	 public String reviewContents(
			 @RequestParam("review_id") int review_id, 
			 Model model,
			 HttpSession session // ȸ����� ��������
			 ) {
		 
		 // ���ǿ��� ����� ��� ��������
		 String grade = (String) session.getAttribute("grade");
	  
		 ReviewVo rv = reviewService.reviewSelectOne(review_id);
	 
	    // ReviewVo���� ���� ����� �����ͼ� �𵨿� �߰�
	    List<String> fileNames = rv.getFileNames();
	    model.addAttribute("rv", rv);
	    model.addAttribute("fileNames", fileNames);
	    model.addAttribute("grade", grade);
	    
	 return "WEB-INF/review/reviewContents"; 
	 
	 }
	 
		// �����ϱ� ���
		@RequestMapping(value = "reviewDelete.aws", method = RequestMethod.GET)
		public String reviewDelete(
				@RequestParam("review_id") int review_id){
			
		    // �Խñ� ���� ���� ȣ��
		    int result = reviewService.reviewDelete(review_id);
		   
		    // ���� �� ��� �������� �����̷�Ʈ
		    return "redirect:/review/reviewList.aws";
		}	 
	 
	
		// �����ϱ� ȭ��
		@RequestMapping(value= "reviewModify.aws", method=RequestMethod.GET)
		public String reviewModify(@RequestParam("review_id") int review_id,Model model) {
				
			ReviewVo rv = reviewService.reviewSelectOne(review_id);
			model.addAttribute("rv", rv);
			
			String path = "WEB-INF/review/reviewModify";
			return path;
		}	

		
		// �����ϱ� ó��
		@RequestMapping(value="reviewModifyAction.aws")
		public String reviewModifyAction(
				ReviewVo rv, // �Խñ� ������ ��� �ִ� Vo ��ü�� �Ű������� ����
		        @RequestParam("attachfile") MultipartFile[] attachfiles,
		        HttpServletRequest request,
		        RedirectAttributes rttr
				) throws Exception {
			
		    String uploadPath = "D:/dev/bakeryUpload"; // ���ε� ���
		    List<MultipartFile> fileList = Arrays.asList(attachfiles);
		    List<String> uploadedFileNames = UploadFileUtiles.uploadFiles(uploadPath, fileList);
				
		    // ���� �̸� ����Ʈ�� ���ڿ��� ��ȯ (��: "file1.jpg,file2.png")
		    String uploadedFileNamesStr = String.join(",", uploadedFileNames);	
			
		    rv.setFilename(uploadedFileNamesStr); // ��ȯ�� ���� �̸� ���ڿ� ����
			
			//���� ó��
			int value = reviewService.reviewUpdate(rv); // ���񽺿��� ���� �޼��� ȣ���ϱ�
			
			String path="";
			if(value==0) {
				path = "redirect:/review/reviewModify.aws?review_id="+rv.getReview_id(); // ���������� �ش� Ű���� �ٽ� ������� �ϱ� ������ bidx�� �Ѱž� �Ѵ�
			}else {
				path = "redirect:/review/reviewContents.aws?review_id="+rv.getReview_id();
			}
			return path;
		}		
		
	
	
	
	
	@RequestMapping(value="/displayFile.aws", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(
			@RequestParam("fileName") String fileName,
			@RequestParam(value="down", defaultValue="0") int down   // ȭ�鿡 ������ ���ΰ�. �ٿ�ε� �ϰ� �� ���ΰ�?
			) {
		
		ResponseEntity<byte[]> entity = null;  //��ü�� ��� ���ε� byte�迭�� �� ��´�. 
		InputStream in = null;	// �������� ���ο� ������, ó���� �о���̴� �������� InputStream
		
		try{
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1); // Ȯ���ڰ� �������� �����.
			MediaType mType = MediaUtils.getMediaType(formatName); // ������ Ȯ���ڸ� ������ �̵�� ��ƿ�̶�� ���� �ִ´�. �ֳĸ� ���⼭ Ȯ���ڰ� �������� Ÿ���� �˱� ���ؼ� (png,jpg)
			
			HttpHeaders headers = new HttpHeaders();		
			 
			in = new FileInputStream(uploadPath+fileName);  // ������ �ʱ�ȭ�� �ָ� ��ü�������Ѽ� �ش�Ǵ� ���� ��ġ�� 
			
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
