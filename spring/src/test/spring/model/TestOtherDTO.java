package test.spring.model;

import java.beans.ConstructorProperties;
import java.util.Date;

public class TestOtherDTO {
	
	private String id;
	private String pw;
	private TestDTO testDTO; 

	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getPw() {
		return pw;
	}
	public final void setPw(String pw) {
		this.pw = pw;
	}
	public TestDTO getTestDTO() {
		return testDTO;
	}
	public void setTestDTO(TestDTO testDTO) {
		this.testDTO = testDTO;
	}
	
	public void runOtherDTO() {
		
		System.out.println( "testDTO id : " + testDTO.getId());
		System.out.println( "this id : " + this.getId());
		
		
	}
	
}
