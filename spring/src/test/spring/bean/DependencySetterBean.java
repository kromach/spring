package test.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.model.DependencyDAO;

@Controller
public class DependencySetterBean {

	// Setter 주입 방식 Setter Injection

	private DependencyDAO dDao;

	@Autowired // set 메서드를 생성하여 객체를 주입한다.
	public void setDependencyDAO(DependencyDAO dDao) {
		this.dDao = dDao;
	}
	
	@RequestMapping("diSet.git")
	public String getStr() {
		
		System.out.println(dDao.getDiTestStr());
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
}
