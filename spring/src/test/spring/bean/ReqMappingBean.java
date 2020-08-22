package test.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.model.TestDTO;

@Controller
public class ReqMappingBean {
	
	// 요청 주소 매핑
	@RequestMapping("form.git") // 다른 RequestMapping에 설정되어 있지 않은 모든 경로를 매핑. {} 내의 텍스트는 아무거나 상관 없음.
	public String form() { 
		
		System.out.println("form!");
		
		return "/WEB-INF/views/reqMapping/form.jsp";
	}
	

	// Model을 사용한 파라미터 전달 방법
	// 요청 주소 매핑
	// form 에서 전달하는 데이터를 받을 수 있는 DTO를 매개변수로 설정한 경우,
	// 별도의 Set 처리가 없더라도 dto에 값이 담긴다. 해당 값은 Get 메서드로 출력할 수 있다. 
	@RequestMapping("pro.git") 
	public String pro(TestDTO dto, Model model) { 
		
		// Model 로 파라미터를 전달
		model.addAttribute("id", dto.getId());
		model.addAttribute("pw", dto.getPw());
		model.addAttribute("dto", dto);
		
		return "/WEB-INF/views/reqMapping/pro.jsp";
	}
	
}
