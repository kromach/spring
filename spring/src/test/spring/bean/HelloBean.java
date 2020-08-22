package test.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloBean {

	@RequestMapping("hello.git")
	public String helloInit(String id, Model model) {
		
		model.addAttribute("id", id); // model에 담아서 jsp 페이지로 파라미터 전달
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
}
