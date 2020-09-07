package member.controller.bean;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import member.model.dto.MemberDTO;
import member.service.bean.MemberService;

@Controller
@EnableWebMvc
@RequestMapping("/member/") // 클래스 레벨
public class MemberBean {
	
	private MemberService memberService;
	
	@Autowired
	MemberBean(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("main.git")
	public String main(HttpSession session, HttpServletRequest request) {
		
		// 세션 X, 쿠키 X -- check -1;
		// 세션 X -- check 0
		// 세션 O -- check 1
		int check = -1;
		
		if(session.getAttribute("memId") != null ) {
			
			check = 1;
		} else {
			
			// 세션이 없지만 쿠키가 있는지 이중 체크
			String id = null, pw = null, auto = null;
			
			Cookie[] coo = request.getCookies();
			
			if (coo != null) {
				for (Cookie c : coo) {
					if (c.getName().equals("autoId")) {
						id = c.getValue();
					}
					if (c.getName().equals("autoPw")) {
						pw = c.getValue();
					}
					if (c.getName().equals("autoCh")) {
						auto = c.getValue();
					}
				}
			}
			
			if(auto != null && id != null && pw != null) {
				check = 0;
			}
		}
		
		request.setAttribute("check", check);		

		return "member/main";
	}
	
	@RequestMapping("signupForm.git")
	public String signupForm() {
		
		return "member/signupForm";
	}
	
	@RequestMapping("signupPro.git")
	public String signupPro(@Valid MemberDTO dto, BindingResult bindingResult, MultipartHttpServletRequest request, Model model) throws Exception {
		
		System.out.println(bindingResult.hasErrors());
		
		if(bindingResult.hasErrors()) { //@Valid 오류 가 있는지 확인
			System.out.println("****** error ******");
			List<ObjectError> list = bindingResult.getAllErrors();
			
			for(ObjectError e : list) {
				System.out.println(e.getDefaultMessage());
			}
			
			return "redirect:/member/signupForm.git"; // 오류가 있으면 form 페이지로 리다이렉트 시킨다.
			
		} else {

			try {
				
				String imageNm = memberService.imageUpload(request);
				
				dto.setImage(imageNm);
				
				memberService.insertMember(dto);
				model.addAttribute("result", 1);
			} catch(Exception e) {
				// 오류발생 이력 로그 남기고
				e.printStackTrace(); 
				// 오류를 다시 throw 
				throw e;
			}
			
			return "member/signupPro";
		}
	}
	
	@RequestMapping("login.git")
	public String login() {
		
		return "member/login";
	}
	
	@RequestMapping("loginPro.git")
	public String loginPro(MemberDTO dto, HttpSession session, Model model) throws SQLException {
		
		int check = memberService.loginCheck(dto);
		
		//if(check == 1) {
		//	session.setAttribute("memId", dto.getId());
		//}
		
		System.out.println(check);
		
		model.addAttribute("check", check);
		
		return "member/loginPro";
	}
	
	@RequestMapping("logout.git")
	public String logout(HttpSession session) {
		
		//session.invalidate();
		
		// 로그아웃 처리 -> 서비스로
		memberService.removeSessionAttr("memId");
		
		return "member/main";
	}
	
	@RequestMapping("modify.git")
	public String modify() {
		
		return "member/modify";
	}
	
	@RequestMapping("modifyForm.git")
	public String modifyForm(HttpSession session, Model model) throws SQLException {
		
		MemberDTO dto = memberService.selectMemberInfo();
		

		ServletRequestAttributes sa = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sa.getRequest();

		String path = request.getRealPath("resources/upload");
		
		model.addAttribute("member", dto);
		model.addAttribute("path", path);
		
		return "member/modifyForm";
	}
	
	@RequestMapping("modifyPro.git")
	public String modifyPro(MemberDTO dto, MultipartHttpServletRequest request, Model model) throws Exception {
		//String id = (String)session.getAttribute("memId");
		
		//dto.setId(id);
		
		String imageNm = memberService.imageUpload(request);
		
		if(imageNm != null) dto.setImage(imageNm);
		
		int result = memberService.updateMember(dto);
		//model.addAttribute("check", check);
		model.addAttribute("result", result);
		
		return "member/modifyPro";
	}
	
	@RequestMapping("exitForm.git")
	public String exitForm() {
		return "member/exitForm";
	}
	
	@RequestMapping("exitPro.git")
	public String exitPro(MemberDTO memberDTO, HttpSession session, Model model) throws SQLException {
		
		//String id = (String)session.getAttribute("memId");
		
		//memberDTO.setId(id);
		
		int check = memberService.deleteMember(memberDTO);
		
		// int check = memberService.loginCheck(memberDTO);

		// if(check == 1) {
		//	memberService.deleteMember(memberDTO.getId());
		//	session.invalidate();
		//}
		
		model.addAttribute("check", check);

		return "member/exitPro";
	}
	
	@RequestMapping("idCheckPop.git")
	public String idCheckPop(String id, Model model) throws SQLException {
		
		if (id != null) {
			int result = memberService.idAvailCheck(id);

			model.addAttribute("result", result);
		}
		model.addAttribute("trialId", id);
		
		return "member/idCheckPop";
	}
	
	@RequestMapping("ajaxIdCheckBody.git")
	@ResponseBody // 한글을 바로 보낼수 없다.
	public String ajaxIdCheckBody(String id) throws SQLException {
		
		// System.out.println(id);
		int check = 0;
		if (id != null) {
			check = memberService.idAvailCheck(id);
		}
		
		String result;
		if(check==1) {
			result = "이미 존재합니다.";
		} else {
			result = "사용가능";
		}
		
		return result;
	}
	
	@RequestMapping("ajaxIdCheck.git")
	public ResponseEntity<String> ajaxIdCheck(String id) throws SQLException {
		
		 // 응답헤더 지정
	    HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "text/html; charset=utf-8");

		// System.out.println(id);
		int check = 0;
		if (id != null) {
			check = memberService.idAvailCheck(id);
		}
		
		String result;
		if(check==1) {
			result = "이미 존재합니다.";
		} else {
			result = "사용가능";
		}
		
		// HttpStatus.CREATED : 201. 무언가를 생성한 결과임을 리턴
		return new ResponseEntity<String>(result, resHeaders, HttpStatus.CREATED);
	}
}
