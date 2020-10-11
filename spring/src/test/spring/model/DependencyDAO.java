package test.spring.model;

import org.springframework.stereotype.Repository;

@Repository
public class DependencyDAO {

	public String getDiTestStr() {
		return "dao 테스트";
	}
	
}
