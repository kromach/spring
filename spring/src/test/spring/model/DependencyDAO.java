package test.spring.model;

import org.springframework.stereotype.Component;

@Component
public class DependencyDAO {

	public String getDiTestStr() {
		return "dao 테스트";
	}
	
}
