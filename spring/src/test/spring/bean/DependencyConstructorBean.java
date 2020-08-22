package test.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.model.DependencyDAO;

@Controller
public class DependencyConstructorBean {

	// 생성자 주입 방식 Constructor Injection
	// 이득 1. null을 주입하지 않는 이상 nullPointerException이 일어나지 않는다.
	// 이득 2. 의존관계를 주입하지 않고서 Contrller 객체를 생성할 수 없다. 
	//         의존 관계에 대한 내용을 외부에 노출시킴으로써 컴파일 타임에 오류를 잡아낼 수 있다.
	
	// 보너스 이득. final을 사용할 수 있다.
	// final 로 선언된 레퍼런스 타입 변수는 반드시 선언과 함께 초기화가 되어야 하므로 
	// setter 주입시에는 의존관계 주입을 받을 필드에 final을 선언 할 수 없다.
	// final의 장점은 컨트롤러 내부에서 객체를 바꿔치기 할 수 없다는 점 

	private final DependencyDAO dDao;

	@Autowired
	public DependencyConstructorBean(DependencyDAO dDao) {
		this.dDao = dDao;
	}
	
	@RequestMapping("diConst.git")
	public String getStr() {
		
		System.out.println(dDao.getDiTestStr());
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
}
