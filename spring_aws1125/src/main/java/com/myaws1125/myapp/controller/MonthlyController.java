package com.myaws1125.myapp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.myaws1125.myapp.domain.BoardVo;
import com.myaws1125.myapp.domain.MonthlyVo;
import com.myaws1125.myapp.domain.PageMaker;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.service.MonthlyService;
import com.myaws1125.myapp.util.MediaUtils;
import com.myaws1125.myapp.util.UploadFileUtiles;

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
	
	@Resource(name="uploadPath") // ���ҽ��� ����� �� name���� ����.xml �� ��ϵ� id�̸����� �־���Ѵ�. �̸��� ���� �ָ� ã�Ƽ� ����.
	private String uploadPath;
	
	

	
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
            @RequestParam("attachfile") MultipartFile attachfile, // ���ε�� ������ �ޱ� ���� MultipartFile ��ü
            HttpServletRequest request,
            RedirectAttributes rttr
            )throws Exception {
			MultipartFile file = attachfile; //����� ���� �̸� ������ 
			String uploadedFileName=""; // ������ ���ε�� �� ����� ���ϸ��� ������ ����
			
			if(! file.getOriginalFilename().equals("")) { // �ش� ������ �����Ѵٸ�
				 // ������ ������ �����ϰ� ����� ���� �̸��� ��ȯ����
				uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			}


        // ����� ���� ����
        String midx = request.getSession().getAttribute("midx").toString();
        int midx_int = Integer.parseInt(midx);

        // BoardVo ��ü�� ������ ����
        monv.setMidx(midx_int);
        monv.setUploadedFilename(uploadedFileName);  // vo�� ��Ƽ� ��������

        // �Խñ� ���� ���� ȣ��
        int value = monthlyService.monthlyInsert(monv);
        //System.out.println("value===" + value);

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
	
	
	// �̴��ǻ��� �� ���� ȭ�� ���� 
	@RequestMapping(value= "monthlyContents.aws")
	public String monthlyContents(
			@RequestParam("mbidx") int mbidx, 
			Model model,
			HttpSession session // ȸ����� ��������
			) {
		
	    // ���ǿ��� ����� ��� ��������
	    String grade = (String) session.getAttribute("grade");
		
		monthlyService.monthlyViewCntUpdate(mbidx); // ��ȸ�� �ø���
		MonthlyVo monv = monthlyService.monthlySelectOne(mbidx);
	    // ����� ����� �𵨿� �߰��Ͽ� �信�� Ȱ���� �� �ֵ��� ����
	    model.addAttribute("grade", grade);
		model.addAttribute("monv", monv);
		
		
		String path = "WEB-INF/monthly/monthlyContents";
		return path;
	}	
	
	
	
	// �����ϱ� ���
	@RequestMapping(value = "monthlyDelete.aws", method = RequestMethod.GET)
	public String deleteMonthly(
			@RequestParam("mbidx") int mbidx){
		
	    // �Խñ� ���� ���� ȣ��
	    int result = monthlyService.monthlyDelete(mbidx);
	   
	    // ���� �� ��� �������� �����̷�Ʈ
	    return "redirect:/monthly/monthlyList.aws";
	}
	
	
	// �����ϱ� ȭ��
	@RequestMapping(value= "monthlyModify.aws", method=RequestMethod.GET)
	public String monthlyModify(@RequestParam("mbidx") int mbidx,Model model) {
			
		MonthlyVo monv = monthlyService.monthlySelectOne(mbidx);
		model.addAttribute("monv", monv);
		
		String path = "WEB-INF/monthly/monthlyModify";
		return path;
	}
	
	
	// �����ϱ� ó��
	@RequestMapping(value="monthlyModifyAction.aws")
	public String monthlyModifyAction(
			MonthlyVo monv, // �Խñ� ������ ��� �ִ� Vo ��ü�� �Ű������� ����
			@RequestParam("attachfile") MultipartFile attachfile, // ���ε�� ������ �ޱ� ���� MultipartFile ��ü
			HttpServletRequest request,
			RedirectAttributes rttr
			) throws Exception {
		
		MultipartFile file = attachfile; //����� ���� �̸� ������ 
		String uploadedFileName=""; // ������ ���ε�� �� ����� ���ϸ��� ������ ����
			
		if(! file.getOriginalFilename().equals("")) { // �ش� ������ �����Ѵٸ�
			 // ������ ������ �����ϰ� ����� ���� �̸��� ��ȯ����
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		}		
		
		monv.setUploadedFilename(uploadedFileName);  // vo�� ��Ƽ� �������� 
		
		//���� ó��
		int value = monthlyService.monthlyUpdate(monv); // ���񽺿��� ���� �޼��� ȣ���ϱ�
		
		String path="";
		if(value==0) {
			path = "redirect:/monthly/monthlyModify.aws?mbidx="+monv.getMbidx(); // ���������� �ش� Ű���� �ٽ� ������� �ϱ� ������ bidx�� �Ѱž� �Ѵ�
		}else {
			path = "redirect:/monthly/monthlyContents.aws?mbidx="+monv.getMbidx();
		}
		return path;
	}
	
	
	


	
	
	
	
	
	
	
	
	
	// �̹��� 
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
