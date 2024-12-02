package com.myaws1125.myapp.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.type.BigIntegerTypeHandler;
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
import com.myaws1125.myapp.domain.PageMaker;
import com.myaws1125.myapp.domain.SearchCriteria;
import com.myaws1125.myapp.service.BoardService;
import com.myaws1125.myapp.util.MediaUtils;
import com.myaws1125.myapp.util.UploadFileUtiles;
import com.myaws1125.myapp.util.UserIp;

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
	
	@Autowired(required = false) 
	private UserIp userIp;
	
	
	
	// �۾��� ȭ�� ����
	@RequestMapping(value= "boardWrite.aws", method=RequestMethod.GET)
	public String boardWrite() {
		System.out.println("���� ����Ʈ ");
		String path = "WEB-INF/board/boardWrite";
		return path;
	}
	
	
	// �����Խ��� �� ���� ȭ�� ���� 
	@RequestMapping(value= "boardContents.aws")
	public String boardContents(@RequestParam("bidx") int bidx, Model model) {
		
		boardService.boardViewCntUpdate(bidx); // ��ȸ�� �ø���
		BoardVo bv = boardService.boardSelectOne(bidx);
		model.addAttribute("bv", bv);
		
		String path = "WEB-INF/board/boardContents";
		return path;
	}
	
	
	// �����ϱ� ȭ�� 
	@RequestMapping(value="boardModify.aws")
	public String boardModify(@RequestParam("bidx") int bidx,Model model){
		
		BoardVo bv = boardService.boardSelectOne(bidx);
		model.addAttribute("bv", bv);
		
		String path="WEB-INF/board/boardModify";
		return path;
	}
	
	
	// �Խñ� �����ϱ�
	@RequestMapping(value="boardDelete.aws", method=RequestMethod.GET)
	public String boardDelete(@RequestParam("bidx") int bidx,Model model){
		
		model.addAttribute("bidx",bidx);
		String path="WEB-INF/board/boardDelete";
		
		System.out.println("controller bidx" + bidx);
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
		@RequestParam("attachfile") MultipartFile attachfile, // ���ε�� ������ �ޱ� ���� MultipartFile ��ü
		HttpServletRequest request,
		RedirectAttributes rttr
		) throws Exception { // ���ܿ� ���� �ϴ°�
		System.out.println("boardWriteAction ����");
		MultipartFile file = attachfile; //����� ���� �̸� ������ 
		String uploadedFileName=""; // ������ ���ε�� �� ����� ���ϸ��� ������ ����
		System.out.println("boardWriteAction ����");
		if(! file.getOriginalFilename().equals("")) { // �ش� ������ �����Ѵٸ�
			 // ������ ������ �����ϰ� ����� ���� �̸��� ��ȯ����
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		}
		String midx = request.getSession().getAttribute("midx").toString();
		int midx_int = Integer.parseInt(midx);
		String ip = userIp.getUserIp(request);
		
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
				e.printStackTrace();
			}
		}
		
		
		return entity;
	}
	
	
	
	// �����ϱ� ���
	@RequestMapping(value="boardDeleteAction.aws", method=RequestMethod.POST)
	public String boardDeleteAction(
			@RequestParam("bidx") int bidx, 
			@RequestParam("password") String password,
			HttpSession session,
			RedirectAttributes rttr
			){
		
		System.out.println("���� �׼� ����");
			
		BoardVo bv = boardService.boardSelectOne(bidx);
		int midx = Integer.parseInt(session.getAttribute("midx").toString());
		int value = boardService.boardDelete(bidx,midx,password);
		      
		String path = "";
		if(bv.getMidx()==midx) {
			if(value==1) {
				path = "redirect:/board/boardList.aws";
			}else {
				rttr.addFlashAttribute("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				path = "redirect:/board/boardDelete.aws?bidx="+bidx;
			}
		}else {
			rttr.addFlashAttribute("msg", "���� �� ȸ���� �ƴմϴ�.");
			path = "redirect:/board/boardDelete.aws?bidx="+bidx;
		}
		return path;  // .jsp�� WEB-INF/spring/appServlet/servlet-context.xml > ���� �Ѿ���
	}
	
	
	//�Խ��ǿ��� �� ������ ���� ���ε� ����� ó��
		@RequestMapping(value= "boardModifyAction.aws")
		public String boardModifyAction(
			BoardVo bv, // �Խñ� ������ ��� �ִ� BoardVo ��ü�� �Ű������� ����
			@RequestParam("attachfile") MultipartFile attachfile, // ���ε�� ������ �ޱ� ���� MultipartFile ��ü
			HttpServletRequest request,
			RedirectAttributes rttr
			) throws Exception { // ���ܿ� ���� �ϴ°�
			
			MultipartFile file = attachfile; //����� ���� �̸� ������ 
			String uploadedFileName=""; // ������ ���ε�� �� ����� ���ϸ��� ������ ����
				
			if(! file.getOriginalFilename().equals("")) { // �ش� ������ �����Ѵٸ�
				 // ������ ������ �����ϰ� ����� ���� �̸��� ��ȯ����
				uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			}
			
			String midx = request.getSession().getAttribute("midx").toString();
			int midx_int = Integer.parseInt(midx); 
			String ip = userIp.getUserIp(request);
				
			bv.setUploadedFilename(uploadedFileName);  // vo�� ��Ƽ� �������� 
			bv.setIp(ip);
			
			//���� ó��
			int value = boardService.boardUpdate(bv); // ���񽺿��� ���� �޼��� ȣ���ϱ�
				
			String path="";
			if(bv.getMidx() == midx_int) {
		        if(value == 1) {
		           path = "redirect:/board/boardContents.aws?bidx=" + bv.getBidx();
		        }else {
		             rttr.addFlashAttribute("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		             path = "redirect:/board/boardModify.aws?bidx=" + bv.getBidx();
		         }
		     }else {
		         rttr.addFlashAttribute("msg", "�ڽ��� �Խñ۸� ���� �� �� �ֽ��ϴ�.");
		         path = "redirect:/board/boardModify.aws?bidx=" + bv.getBidx();
		     }
		            
		     return path; 
	}	


}
	
