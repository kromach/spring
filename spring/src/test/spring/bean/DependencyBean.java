package test.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.model.DependencyDAO;

@Controller
public class DependencyBean {

	// 필드 주입 방식 Field Injection
	// 변수 선언부에 어노테이션을 사용해 의존성을 주입한다.
	@Autowired
	private DependencyDAO dependencyDAO = null;
	
	
	@RequestMapping("di.git")
	public String getStr() {
		
		System.out.println(dependencyDAO.getDiTestStr());
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
}
